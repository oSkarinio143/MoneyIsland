package pl.oskarinio.moneyisland.auth.application.port;

import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;
import pl.oskarinio.moneyisland.zzzzzzzz.auth.LoginForm;

public interface Login {
    UserServiceData loginUser(LoginForm loginForm);
}
