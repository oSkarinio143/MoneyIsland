package pl.oskarinio.moneyisland.finance.infrastructure.adapter.in;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarinio.moneyisland.finance.application.port.credit.AddCreditUseCase;
import pl.oskarinio.moneyisland.finance.application.port.credit.DeleteCreditUseCase;
import pl.oskarinio.moneyisland.finance.application.port.credit.LoadCreditsUseCase;
import pl.oskarinio.moneyisland.finance.application.port.credit.PayRepaymentUseCase;
import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditAddForm;
import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditDeleteForm;
import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditPayRepaymentForm;
import pl.oskarinio.moneyisland.shared.config.Route;

@Controller
@RequestMapping(Route.MAIN + Route.FINANCE + Route.USER + Route.CREDIT)
public class CreditController {
    private final LoadCreditsUseCase loadCreditsUseCase;
    private final AddCreditUseCase addCreditUseCase;
    private final PayRepaymentUseCase payRepaymentUseCase;
    private final DeleteCreditUseCase deleteCreditUseCase;
    private static final String USERNAME = "username";
    private static final String ROUTE_CREDIT = Route.REDIRECT + Route.FINANCE + Route.USER + Route.CREDIT;

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
        return ROUTE_CREDIT;
    }

    @PostMapping(Route.PAY)
    public String payRepayment(@ModelAttribute CreditPayRepaymentForm creditPayRepaymentForm,
                               RedirectAttributes redirectAttributes){

        payRepaymentUseCase.payRepayment(creditPayRepaymentForm);
        redirectAttributes.addAttribute(USERNAME, creditPayRepaymentForm.getUsername());
        return ROUTE_CREDIT;
    }

    @PostMapping(Route.DELETE)
    public String deleteCredit(@ModelAttribute CreditDeleteForm creditDeleteForm,
                               RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute(USERNAME, creditDeleteForm.getUsername());
        deleteCreditUseCase.deleteCredit(creditDeleteForm);
        return ROUTE_CREDIT;
    }

}
