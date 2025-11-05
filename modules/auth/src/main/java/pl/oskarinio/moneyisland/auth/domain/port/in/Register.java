package pl.oskarinio.moneyisland.auth.domain.port.in;


import pl.oskarinio.moneyisland.auth.domain.model.form.RegisterForm;
import pl.oskarinio.moneyisland.auth.domain.model.user.UserServiceData;

public interface Register {
    UserServiceData registerUser(RegisterForm registerForm);
}
