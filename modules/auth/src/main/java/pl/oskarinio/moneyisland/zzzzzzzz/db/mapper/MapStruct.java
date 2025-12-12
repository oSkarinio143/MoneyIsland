package pl.oskarinio.moneyisland.zzzzzzzz.db.mapper;

import org.mapstruct.Mapper;
import pl.oskarinio.moneyisland.zzzzzzzz.auth.LoginForm;
import pl.oskarinio.moneyisland.zzzzzzzz.auth.LoginFormRequest;
import pl.oskarinio.moneyisland.zzzzzzzz.auth.RegisterForm;
import pl.oskarinio.moneyisland.zzzzzzzz.auth.RegisterFormRequest;

@Mapper(componentModel = "spring")
public interface MapStruct {

    LoginForm toLoginForm(LoginFormRequest loginFormRequest);
    RegisterForm toRegisterForm(RegisterFormRequest registerFormRequest);

}
