package pl.oskarinio.moneyisland.auth.application.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.auth.application.port.RegisterUseCase;
import pl.oskarinio.moneyisland.auth.domain.port.PasswordEncoderPort;
import pl.oskarinio.moneyisland.auth.domain.service.RegisterDomainService;
import pl.oskarinio.moneyisland.auth.domain.dto.form.RegisterForm;
import pl.oskarinio.moneyisland.auth.infrastructure.kafka.KafkaEventPublisher;
import pl.oskarinio.moneyisland.auth.domain.port.UserManagement;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;

import java.time.Clock;

@Service
@Slf4j
public class RegisterService implements RegisterUseCase {
    private final RegisterDomainService registerDomainService;

    @Autowired
    private KafkaEventPublisher kafkaEventPublisher;

    public RegisterService(UserRepository userRepository,
                           UserManagement userManagement,
                           PasswordEncoderPort passwordEncoderPort,
                           Clock clock,
                           @Value("${token.refresh.seconds}") long refreshSeconds) {
        this.registerDomainService = new RegisterDomainService(userRepository, userManagement, passwordEncoderPort, clock, refreshSeconds);
    }

    @Override
    @Transactional
    public UserServiceData registerUser(RegisterForm registerForm) {
        log.debug("Rejestruję użytkownika. Nazwa = {}", registerForm.getUsername());
        UserServiceData usrData = registerDomainService.registerUser(registerForm);
        System.out.println("powinno po tym wyslac event");
        kafkaEventPublisher.publishUserRegistered(usrData.getUsername());
        System.out.println("powinien przed chwila zostac wyslany event");
        return usrData;

        //return registerUseCase.registerUser(registerForm);
    }
}
