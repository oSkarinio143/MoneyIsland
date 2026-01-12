package pl.oskarinio.moneyisland.shared.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.oskarinio.moneyisland.shared.domain.exception.RegistrationDataException;
import pl.oskarinio.moneyisland.shared.domain.exception.UsernameNotFoundException;
import pl.oskarinio.moneyisland.shared.domain.exception.UsernameNotMatchingPassword;

@Slf4j
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler (exception = {
            UsernameNotMatchingPassword.class,
            UsernameNotFoundException.class
    })
    public String handleUsernameLogin(HttpServletRequest request){
        String urlFromForm = request.getAttribute("requestUrl").toString();
        return Route.LOCALHOST + urlFromForm + "?loginError=BladLogowania";
    }

    @ExceptionHandler (exception = {
            DataIntegrityViolationException.class,
            RegistrationDataException.class
    })
    public String handleUsernameRegister(HttpServletRequest request){
        String urlFromForm = request.getAttribute("requestUrl").toString();
        return Route.LOCALHOST + urlFromForm + "?registerError=BladRejestracji";
    }
}
