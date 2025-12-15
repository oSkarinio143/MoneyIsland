package pl.oskarinio.moneyisland.auth.application.port;

import pl.oskarinio.moneyisland.auth.domain.dto.UserServiceData;
import pl.oskarinio.moneyisland.auth.domain.dto.form.RegisterForm;

public interface RegisterUseCase {
    UserServiceData registerUser(RegisterForm registerForm);
}
