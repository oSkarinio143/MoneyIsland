package pl.oskarinio.moneyisland.finance;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.oskarinio.moneyisland.shared.config.Route;

@Controller
@RequestMapping(Route.MAIN)
public class FinanceController {
    @GetMapping()
    public String displayMainPanel(Model model){
        model.addAttribute("activePage", "mainPanel");
        return Route.VIEW_MAIN_PANEL;
    }

    @GetMapping(Route.USER + Route.FINANCE)
    public String displayFinancePanel(Model model){
        model.addAttribute("activePage", "finance");
        return "finance";
    }

    @PostMapping(Route.USER + Route.FINANCE + "/balance")
    public String displayUserBalancePanel(){

        return "balance";
    }
}
