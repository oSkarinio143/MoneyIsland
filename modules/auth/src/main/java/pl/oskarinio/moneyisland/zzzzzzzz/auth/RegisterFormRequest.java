package pl.oskarinio.moneyisland.zzzzzzzz.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pl.oskarinio.moneyisland.zzzzzzzz.validation.ValidSamePassword;

@Getter
@Setter
@ValidSamePassword
public class RegisterFormRequest {

    @NotBlank(message = "Nazwa użytkownika jest wymagana, nie może pozostać pusta")
    @Size(min = 4, max = 16, message = "Nazwa użytkownika musi mieć od 4 do 16 znaków")
    private String username;

    @NotBlank(message = "Email jest wymagany, nie może pozostać pusty")
    @Size(min = 4, message = "Email musi mieć więcej niż 4 znaki")
    private String email;

    @NotBlank(message = "Hasło jest wymagane, nie może pozostać puste")
    @Size(min = 4, max = 16, message = "Hasło musi mieć od 4 do 16 znaków")
    private String password;

    @NotBlank(message = "Potwierdzenie Hasła jest wymagane, nie może pozostać puste")
    @Size(min = 4, max = 16, message = "Potwierdzenie hasła musi mieć od 4 do 16 znaków")
    private String confirmPassword;
}
