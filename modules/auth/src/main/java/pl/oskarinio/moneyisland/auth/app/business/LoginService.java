package pl.oskarinio.moneyisland.auth.app.business;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.auth.domain.model.form.LoginForm;
import pl.oskarinio.moneyisland.auth.domain.model.user.UserServiceData;
import pl.oskarinio.moneyisland.auth.domain.port.in.Login;
import pl.oskarinio.moneyisland.auth.domain.port.in.UserManagement;
import pl.oskarinio.moneyisland.auth.domain.port.out.PasswordEncoderPort;
import pl.oskarinio.moneyisland.auth.domain.usecase.LoginUseCase;

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
