package pl.oskarinio.moneyisland.auth.application.port;

import pl.oskarinio.moneyisland.auth.domain.dto.UserServiceData;
import pl.oskarinio.moneyisland.auth.domain.dto.form.LoginForm;

public interface LoginUseCase {
    UserServiceData loginUser(LoginForm loginForm);
}
