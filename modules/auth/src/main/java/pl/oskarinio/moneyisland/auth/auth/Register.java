package pl.oskarinio.moneyisland.auth.auth;

import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;

public interface Register {
    UserServiceData registerUser(RegisterForm registerForm);
}
