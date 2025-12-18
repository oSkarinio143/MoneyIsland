package pl.oskarinio.moneyisland.auth.infrastructure.adapter.in.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarinio.moneyisland.auth.application.port.LoginUseCase;
import pl.oskarinio.moneyisland.auth.application.port.RegisterUseCase;
import pl.oskarinio.moneyisland.auth.domain.dto.UserServiceData;
import pl.oskarinio.moneyisland.auth.domain.port.UserManagement;
import pl.oskarinio.moneyisland.auth.infrastructure.adapter.in.model.LoginFormRequest;
import pl.oskarinio.moneyisland.auth.infrastructure.adapter.in.model.RegisterFormRequest;
import pl.oskarinio.moneyisland.auth.infrastructure.db.mapper.MapStruct;
import pl.oskarinio.moneyisland.auth.infrastructure.service.CookieManager;
import pl.oskarinio.moneyisland.shared.config.Route;

import java.util.List;

@Slf4j
@RequestMapping(Route.MAIN)
@Controller
public class UserController {

    private final UserManagement userManagement;
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final CookieManager cookieManager;
    private final MapStruct mapper;

    public UserController(UserManagement userManagement, RegisterUseCase registerUseCase, LoginUseCase loginUseCase, CookieManager cookieManager, MapStruct mapper) {
        this.userManagement = userManagement;
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.cookieManager = cookieManager;
        this.mapper = mapper;
    }

    @PostMapping(Route.LOGIN)
    public String login(@Valid @ModelAttribute LoginFormRequest loginFormRequest,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes,
                        HttpServletResponse response) {

        log.info("Uzytkownik rozpoczyna logowanie");
        if (bindingResult.hasErrors()){
            log.warn("Logowanie nie udane, wprowadzono niepoprawne dane");
            List<String> errorMessages = bindingResult.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList();
            redirectAttributes.addFlashAttribute("errorMessage", userManagement.prepareErrorMessage(errorMessages));
            return Route.REDIRECT + Route.LOGIN;
        }
        UserServiceData userServiceData = loginUseCase.loginUser(mapper.toLoginForm(loginFormRequest));
        cookieManager.setCookieTokens(userServiceData, response);
        redirectAttributes.addFlashAttribute("welcomeUserMessage","Udało się poprawnie zalogować użytkownika");
        log.info("Uzytkownik zostal zalogowany");
        return Route.REDIRECT;
    }

    @PostMapping(Route.REGISTER)
    public String register(@Valid @ModelAttribute RegisterFormRequest registerFormRequest,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpServletResponse response){

        log.info("Uzytkownik rozpoczyna rejestracje");
        if(bindingResult.hasErrors()) {
            log.warn("Rejestracja nie udana, wprowadzono niepoprawne dane");
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(v -> v.getDefaultMessage())
                    .toList();
            redirectAttributes.addFlashAttribute("errorMessage", userManagement.prepareErrorMessage(errorMessages));
            return Route.REDIRECT + Route.REGISTER;
        }
        UserServiceData userServiceData = registerUseCase.registerUser(mapper.toRegisterForm(registerFormRequest));
        cookieManager.setCookieTokens(userServiceData, response);
        redirectAttributes.addFlashAttribute("welcomeUserMessage", "Udało się zarejestrować użytkownika");
        log.info("Uzytkownik zostal zarejestrowany");
        return Route.REDIRECT;
    }

    @PostMapping(Route.LOGOUT)
    public String logoutUser(RedirectAttributes redirectAttributes,
                             HttpServletResponse response,
                             HttpServletRequest request){

        log.info("Uzytkownik rozpoczyna wylogowanie");
        String username = cookieManager.getUsernameFromCookie(request);
        cookieManager.removeAccessCookie(response);
        cookieManager.removeRefreshCookie(response);
        userManagement.deleteToken(username);

        redirectAttributes.addFlashAttribute("logoutMessage", "Użytkownik został wylogowany");
        log.info("Uzytkownik zostal wylogowany");
        return Route.REDIRECT;
    }
}
