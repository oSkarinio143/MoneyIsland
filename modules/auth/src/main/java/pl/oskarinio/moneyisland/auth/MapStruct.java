package pl.oskarinio.moneyisland.auth;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStruct {

    LoginForm toLoginForm(LoginFormRequest loginFormRequest);
    RegisterForm toRegisterForm(RegisterFormRequest registerFormRequest);
}
