package pl.oskarinio.moneyisland.auth.domain.dto.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterForm {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;

}
