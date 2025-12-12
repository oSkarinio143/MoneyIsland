package pl.oskarinio.moneyisland.auth.application.port;

import pl.oskarinio.moneyisland.shared.uncategorized.UserServiceData;
import pl.oskarinio.moneyisland.zzzzzzzz.auth.RegisterForm;

public interface Register {
    UserServiceData registerUser(RegisterForm registerForm);
}
