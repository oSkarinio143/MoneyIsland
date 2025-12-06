package pl.oskarinio.moneyisland.auth.db.mapper;

import org.mapstruct.Mapper;
import pl.oskarinio.moneyisland.auth.auth.LoginForm;
import pl.oskarinio.moneyisland.auth.auth.LoginFormRequest;
import pl.oskarinio.moneyisland.auth.auth.RegisterForm;
import pl.oskarinio.moneyisland.auth.auth.RegisterFormRequest;

@Mapper(componentModel = "spring")
public interface MapStruct {

    LoginForm toLoginForm(LoginFormRequest loginFormRequest);
    RegisterForm toRegisterForm(RegisterFormRequest registerFormRequest);

}
