package pl.oskarinio.moneyisland.auth.application.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.auth.application.port.LoginUseCase;
import pl.oskarinio.moneyisland.auth.domain.port.PasswordEncoderPort;
import pl.oskarinio.moneyisland.auth.domain.port.UserManagement;
import pl.oskarinio.moneyisland.auth.domain.service.LoginDomainService;
import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;
import pl.oskarinio.moneyisland.auth.domain.dto.form.LoginForm;

@Service
@Slf4j
public class LoginService implements LoginUseCase {
    private final LoginDomainService loginDomainService;

    public LoginService(UserManagement userManagement, PasswordEncoderPort passwordEncoderPort) {
        this.loginDomainService = new LoginDomainService(userManagement, passwordEncoderPort);
    }

    @Transactional
    @Override
    public UserServiceData loginUser(LoginForm loginForm) {
        log.debug("Rozpoczynam proces logowania. Nazwa = {}", loginForm.getUsername());
        return loginDomainService.loginUser(loginForm);
    }
}
