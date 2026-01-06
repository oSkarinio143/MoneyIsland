package pl.oskarinio.moneyisland.finance.infrastructure.adapter.in;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarinio.moneyisland.finance.application.port.target.LoadExpenseTargetUseCase;
import pl.oskarinio.moneyisland.finance.application.port.target.LoadIncomeTargetUseCase;
import pl.oskarinio.moneyisland.finance.application.port.target.SaveTargetValuesUseCase;
import pl.oskarinio.moneyisland.finance.application.port.history.LoadExpenseDataUseCase;
import pl.oskarinio.moneyisland.finance.application.port.history.LoadIncomeDataUseCase;
import pl.oskarinio.moneyisland.finance.domain.dto.form.TargetForm;
import pl.oskarinio.moneyisland.shared.config.Route;

@Controller
@RequestMapping(Route.MAIN + Route.USER + Route.FINANCE + Route.GOAL)
public class TargetController {
    private final LoadIncomeDataUseCase loadIncomeDataUseCase;
    private final LoadExpenseDataUseCase loadExpenseDataUseCase;
    private final LoadIncomeTargetUseCase loadIncomeTargetUseCase;
    private final LoadExpenseTargetUseCase loadExpenseTargetUseCase;
    private final SaveTargetValuesUseCase saveTargetValuesUseCase;

    public TargetController(LoadIncomeDataUseCase loadIncomeDataUseCase, LoadExpenseDataUseCase loadExpenseDataUseCase, LoadIncomeTargetUseCase loadIncomeTargetUseCase, LoadExpenseTargetUseCase loadExpenseTargetUseCase, SaveTargetValuesUseCase saveTargetValuesUseCase) {
        this.loadIncomeDataUseCase = loadIncomeDataUseCase;
        this.loadExpenseDataUseCase = loadExpenseDataUseCase;
        this.loadIncomeTargetUseCase = loadIncomeTargetUseCase;
        this.loadExpenseTargetUseCase = loadExpenseTargetUseCase;
        this.saveTargetValuesUseCase = saveTargetValuesUseCase;
    }

    @ModelAttribute()
    public void setActivePage(Model model) {
        model.addAttribute("activePage", Route.VIEW_FINANCE);
    }

    @GetMapping()
    public String displayMainPanel(Model model,
                                   @RequestParam String username) {
        model.addAttribute("incomeCurrent", loadIncomeDataUseCase.loadIncomeData(username));
        model.addAttribute("expenseCurrent", loadExpenseDataUseCase.loadExpenseData(username));
        model.addAttribute("incomeTarget", loadIncomeTargetUseCase.loadIncomeTarget(username));
        model.addAttribute("expenseTarget", loadExpenseTargetUseCase.loadExpenseTarget(username));
        return Route.VIEW_GOAL;
    }

    @PostMapping()
    public String saveTargets(RedirectAttributes redirectAttributes,
                              @ModelAttribute TargetForm targetForm){

        saveTargetValuesUseCase.saveTargetValues(targetForm);
        redirectAttributes.addAttribute("username", targetForm.getUsername());
        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.GOAL;
    }
}
