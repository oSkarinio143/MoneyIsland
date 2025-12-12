package pl.oskarinio.moneyisland.auth.application.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.auth.domain.port.PasswordEncoderPort;
import pl.oskarinio.moneyisland.auth.application.port.Login;
import pl.oskarinio.moneyisland.auth.application.port.different.UserManagement;
import pl.oskarinio.moneyisland.auth.domain.service.LoginUseCase;
import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;
import pl.oskarinio.moneyisland.zzzzzzzz.auth.LoginForm;

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
