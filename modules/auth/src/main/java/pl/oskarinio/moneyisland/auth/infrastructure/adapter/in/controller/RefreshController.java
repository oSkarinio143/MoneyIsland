package pl.oskarinio.moneyisland.auth.infrastructure.adapter.in.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.oskarinio.moneyisland.shared.config.Route;

@RequestMapping(Route.MAIN)
@Controller
public class RefreshController {
    @GetMapping(Route.REFRESH)
    public String refreshToken(HttpServletRequest httpServletRequest){
        return Route.REDIRECT;
    }
}
