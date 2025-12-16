package pl.oskarinio.moneyisland.auth.infrastructure.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.auth.domain.dto.User;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.shared.kafka.UserDeletedEvent;
import pl.oskarinio.moneyisland.shared.kafka.UserRegisteredEvent;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public void publishUserRegistered(String username) {
        Optional<User> userEntity = userRepository.findByUsername(username);
        if(userEntity.isEmpty())
            return;

        User user = userEntity.get();
        UserRegisteredEvent event = new UserRegisteredEvent(user.getId(), username);
        try {
            log.info("Wysyłam event rejestracji użytkownika: {} o id - {}", username, user.getId());
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("userRegistered", String.valueOf(user.getId()), message);

        } catch (JsonProcessingException e) {
            log.error("Error podczas wysyłania eventu");
            throw new RuntimeException(e);
        }
    }

    public void publishUserDeleted(String username, Long id) {
        UserDeletedEvent event = new UserDeletedEvent(id, username);
        try{
            log.info("Wysyłam event usunięcia użytkownika: {} o id - {}", username, id);
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("userDeleted", String.valueOf(id), message);

        } catch (JsonProcessingException e) {
            log.error("Error podczas wysyłania eventu");
            throw new RuntimeException(e);
        }
    }
}
