package pl.oskarinio.moneyisland.auth.domain.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginForm {

    private String username;
    private String password;

}