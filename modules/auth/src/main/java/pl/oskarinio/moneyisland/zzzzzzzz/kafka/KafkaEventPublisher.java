package pl.oskarinio.moneyisland.zzzzzzzz.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.shared.uncategorized.User;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    private static final String TOPIC_USER_REGISTRATION = "user-registrations";

    public void publishUserRegistered(String username) {
        Optional<User> userEntity = userRepository.findByUsername(username);
        if(userEntity.isEmpty())
            return;

        User user = userEntity.get();
        UserRegisteredEvent event = new UserRegisteredEvent(user.getId(), username, user.getEmail());
        try {
            log.info("Wysyłam event rejestracji użytkownika: {}", username);
            String message = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(TOPIC_USER_REGISTRATION, String.valueOf(user.getId()), message)
                    .whenComplete((result, ex) -> {
                        if(ex == null){
                            log.info("Wysłano event: {}", message);
                        } else {
                            log.error("Błąd wysyłania eventu: {}", message, ex);
                        }
                    });
        } catch (JsonProcessingException e) {
            log.error("Error podczas wysyłania");
            throw new RuntimeException(e);
        }
    }
}
