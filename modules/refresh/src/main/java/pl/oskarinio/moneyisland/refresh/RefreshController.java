package pl.oskarinio.moneyisland.refresh;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.oskarinio.moneyisland.shared.uncategorized.Route;

@RequestMapping(Route.MAIN)
@Controller
public class RefreshController {

    @GetMapping("/refresh")
    public String refreshToken(HttpServletRequest httpServletRequest){
        return "/refreshed";
    }
}
