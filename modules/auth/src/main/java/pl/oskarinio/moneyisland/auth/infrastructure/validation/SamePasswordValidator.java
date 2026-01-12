package pl.oskarinio.moneyisland.auth.infrastructure.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.oskarinio.moneyisland.auth.infrastructure.adapter.in.model.RegisterFormRequest;

public class SamePasswordValidator implements ConstraintValidator<ValidSamePassword, RegisterFormRequest> {

    @Override
    public boolean isValid(RegisterFormRequest loginForm, ConstraintValidatorContext constraintValidatorContext) {
        return loginForm.getPassword().equals(loginForm.getConfirmPassword());
    }
}
