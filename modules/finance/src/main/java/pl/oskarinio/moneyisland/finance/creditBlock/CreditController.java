package pl.oskarinio.moneyisland.finance.creditBlock;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarinio.moneyisland.shared.config.Route;

@Controller
@RequestMapping(Route.MAIN + Route.USER + Route.FINANCE + Route.CREDIT)
public class CreditController {
    private final LoadCreditsUseCase loadCreditsUseCase;
    private final AddCreditUseCase addCreditUseCase;
    private final PayRepaymentUseCase payRepaymentUseCase;
    private final DeleteCreditUseCase deleteCreditUseCase;
    private static final String USERNAME = "username";

    public CreditController(LoadCreditsUseCase loadCreditsUseCase, PayRepaymentUseCase payRepaymentUseCase, AddCreditUseCase addCreditUseCase, DeleteCreditUseCase deleteCreditUseCase) {
        this.loadCreditsUseCase = loadCreditsUseCase;
        this.addCreditUseCase = addCreditUseCase;
        this.payRepaymentUseCase = payRepaymentUseCase;
        this.deleteCreditUseCase = deleteCreditUseCase;
    }

    @ModelAttribute()
    public void setActivePage(Model model){
        model.addAttribute("activePage", Route.VIEW_FINANCE);
    }

    @GetMapping()
    public String displayCreditPanel(Model model,
                                     @RequestParam String username){

        model.addAttribute("credits", loadCreditsUseCase.loadCredits(username));
        return Route.VIEW_CREDIT;
    }

    @PostMapping(Route.ADD)
    public String addCredit(@ModelAttribute CreditAddForm creditAddForm,
                               RedirectAttributes redirectAttributes){

        addCreditUseCase.addCredit(creditAddForm);
        redirectAttributes.addAttribute(USERNAME, creditAddForm.getUsername());
        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.CREDIT;
    }

    @PostMapping(Route.PAY)
    public String payRepayment(@ModelAttribute CreditPayRepaymentForm creditPayRepaymentForm,
                               RedirectAttributes redirectAttributes){

        payRepaymentUseCase.payRepayment(creditPayRepaymentForm);
        redirectAttributes.addAttribute(USERNAME, creditPayRepaymentForm.getUsername());
        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.CREDIT;
    }

    @PostMapping(Route.DELETE)
    public String deleteCredit(@ModelAttribute CreditDeleteForm creditDeleteForm,
                               RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute(USERNAME, creditDeleteForm.getUsername());
        deleteCreditUseCase.deleteCredit(creditDeleteForm);
        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.CREDIT;
    }

}
