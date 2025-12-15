package pl.oskarinio.moneyisland.auth.infrastructure.db.mapper;

import org.mapstruct.Mapper;
import pl.oskarinio.moneyisland.auth.domain.dto.form.LoginForm;
import pl.oskarinio.moneyisland.auth.infrastructure.adapter.in.model.LoginFormRequest;
import pl.oskarinio.moneyisland.auth.domain.dto.form.RegisterForm;
import pl.oskarinio.moneyisland.auth.infrastructure.adapter.in.model.RegisterFormRequest;

@Mapper(componentModel = "spring")
public interface MapStruct {

    LoginForm toLoginForm(LoginFormRequest loginFormRequest);
    RegisterForm toRegisterForm(RegisterFormRequest registerFormRequest);

}
