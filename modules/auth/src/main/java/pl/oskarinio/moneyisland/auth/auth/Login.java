package pl.oskarinio.moneyisland.auth.auth;

import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;

public interface Login {
    UserServiceData loginUser(LoginForm loginForm);
}
