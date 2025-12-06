package pl.oskarinio.moneyisland.auth;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.shared.uncategorized.UserManagement;
import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;

@Service
@Slf4j
public class LoginService implements Login {
    private final LoginUseCase loginUseCase;

    public LoginService(UserManagement userManagement, PasswordEncoderPort passwordEncoderPort) {
        this.loginUseCase = new LoginUseCase(userManagement, passwordEncoderPort);
    }

    @Transactional
    @Override
    public UserServiceData loginUser(LoginForm loginForm) {
        log.debug("Rozpoczynam proces logowania. Nazwa = {}", loginForm.getUsername());
        return loginUseCase.loginUser(loginForm);
    }
}
