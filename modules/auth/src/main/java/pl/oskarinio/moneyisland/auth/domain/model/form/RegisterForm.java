package pl.oskarinio.moneyisland.auth.domain.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterForm {

    private String username;
    private String password;
    private String confirmPassword;

}
