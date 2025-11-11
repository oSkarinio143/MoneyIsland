package pl.oskarinio.moneyisland.refresh;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.oskarinio.moneyisland.shared.uncategorized.Route;

@RequestMapping(Route.MAIN)
@Controller
public class RefreshController {

    //Może zrobić aby przkierowanie było na konkretny endpoint na którym miał być user
    @GetMapping(Route.REFRESH)
    public String refreshToken(HttpServletRequest httpServletRequest){
        return Route.REDIRECT;
    }
}
