package pl.oskarinio.moneyisland.finance.Kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.User;
import pl.oskarinio.moneyisland.finance.UserRepository;
import pl.oskarinio.moneyisland.shared.kafka.UserDeletedEvent;
import pl.oskarinio.moneyisland.shared.kafka.UserRegisteredEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @KafkaListener(topics = "userRegistered", groupId = "auth")
    public void consumeUserRegistered(String message) {
        log.info("Odebrano wiadomość: {}", message);

        try{
            UserRegisteredEvent event = objectMapper.readValue(message, UserRegisteredEvent.class);
            User user = new User(event.id(), event.username());
            userRepository.save(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(
            topics = "userDeleted",
            groupId = "auth")
    public void consumeUserDeleted(String message){
        log.info("Odebrano wiadomość: {}", message);

        try{
            UserDeletedEvent event = objectMapper.readValue(message, UserDeletedEvent.class);
            userRepository.delete(event.username());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}