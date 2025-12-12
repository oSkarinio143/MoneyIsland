package pl.oskarinio.moneyisland.zzzzzzzz.auth;

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
