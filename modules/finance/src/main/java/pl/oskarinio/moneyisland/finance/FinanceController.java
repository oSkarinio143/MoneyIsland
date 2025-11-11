package pl.oskarinio.moneyisland.finance;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Route.MAIN)
public class FinanceController {
    @GetMapping()
    public String displayFinancePage(){
        return Route.VIEW_MODE;
    }
}
