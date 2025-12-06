package pl.oskarinio.moneyisland.auth;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.oskarinio.moneyisland.auth.auth.RegisterFormRequest;

//Użytkownik musi podać to samo hasło przy rejestracji
public class SamePasswordValidator implements ConstraintValidator<ValidSamePassword, RegisterFormRequest> {

    @Override
    public boolean isValid(RegisterFormRequest loginForm, ConstraintValidatorContext constraintValidatorContext) {
        return loginForm.getPassword().equals(loginForm.getConfirmPassword());
    }
}
