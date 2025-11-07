package pl.oskarinio.moneyisland.auth;

import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;

public interface Register {
    UserServiceData registerUser(RegisterForm registerForm);
}
