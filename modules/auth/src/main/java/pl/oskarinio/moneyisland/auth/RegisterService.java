package pl.oskarinio.moneyisland.auth;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.shared.uncategorized.UserManagement;
import pl.oskarinio.moneyisland.shared.uncategorized.UserRepository;
import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;

import java.time.Clock;

@Service
@Slf4j
public class RegisterService implements Register {
    private final RegisterUseCase registerUseCase;

    public RegisterService(UserRepository userRepository,
                           UserManagement userManagement,
                           PasswordEncoderPort passwordEncoderPort,
                           Clock clock,
                           @Value("${token.refresh.seconds}") long refreshSeconds) {
        this.registerUseCase = new RegisterUseCase(userRepository, userManagement, passwordEncoderPort, clock, refreshSeconds);
    }

    @Override
    @Transactional
    public UserServiceData registerUser(RegisterForm registerForm) {
        log.debug("Rejestruję użytkownika. Nazwa = {}", registerForm.getUsername());
        return registerUseCase.registerUser(registerForm);
    }
}
