package pl.oskarinio.moneyisland.auth.domain.port.in;

import pl.oskarinio.moneyisland.auth.domain.model.form.LoginForm;
import pl.oskarinio.moneyisland.auth.domain.model.user.UserServiceData;

public interface Login {
    UserServiceData loginUser(LoginForm loginForm);
}
