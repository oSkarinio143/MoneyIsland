package pl.oskarinio.moneyisland.finance;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.oskarinio.moneyisland.shared.config.Route;

@Controller
@RequestMapping(Route.MAIN)
public class FinanceController {
    private static final String ACTIVE_PAGE = "activePage";

    @GetMapping()
    public String displayMainPanel(Model model){
        model.addAttribute(ACTIVE_PAGE, Route.VIEW_MAIN_PANEL);
        return Route.VIEW_MAIN_PANEL;
    }

    @GetMapping(Route.USER + Route.FINANCE)
    public String displayFinancePanel(Model model){
        model.addAttribute(ACTIVE_PAGE, Route.VIEW_FINANCE);
        return Route.VIEW_FINANCE;
    }
}
