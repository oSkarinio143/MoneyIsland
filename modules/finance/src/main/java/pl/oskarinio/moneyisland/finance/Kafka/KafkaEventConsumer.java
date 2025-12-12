package pl.oskarinio.moneyisland.finance.Kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {

    private final ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = "user-registered", groupId = "finance-service-group")
    public void consumeUserRegistered(String message) {
        log.info("Odebrano wiadomość: {}", message);

        try{
            UserRegisteredEvent event = objectMapper.readValue(message, UserRegisteredEvent.class);
            System.out.println(event.username());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}