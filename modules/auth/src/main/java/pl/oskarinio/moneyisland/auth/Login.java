package pl.oskarinio.moneyisland.auth;

import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;

public interface Login {
    UserServiceData loginUser(LoginForm loginForm);
}
