package pl.oskarinio.moneyisland.finance.GoalBlock;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarinio.moneyisland.finance.historyBlock.service.LoadExpenseDataUseCase;
import pl.oskarinio.moneyisland.finance.historyBlock.service.LoadIncomeDataUseCase;
import pl.oskarinio.moneyisland.shared.config.Route;

@Controller
@RequestMapping(Route.MAIN + Route.USER + Route.FINANCE + Route.GOAL)
public class GoalController {
    private final LoadIncomeDataUseCase loadIncomeDataUseCase;
    private final LoadExpenseDataUseCase loadExpenseDataUseCase;
    private final LoadIncomeTargetUseCase loadIncomeTargetUseCase;
    private final LoadExpenseTargetUseCase loadExpenseTargetUseCase;
    private final SaveTargetValuesUseCase saveTargetValuesUseCase;

    public GoalController(LoadIncomeDataUseCase loadIncomeDataUseCase, LoadExpenseDataUseCase loadExpenseDataUseCase, LoadIncomeTargetUseCase loadIncomeTargetUseCase, LoadExpenseTargetUseCase loadExpenseTargetUseCase, SaveTargetValuesUseCase saveTargetValuesUseCase) {
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
                              @ModelAttribute GoalFormRequest goalFormRequest){
        saveTargetValuesUseCase.saveTargetValues(goalFormRequest);
        redirectAttributes.addAttribute("username", goalFormRequest.getUsername());
        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.GOAL;
    }
}
