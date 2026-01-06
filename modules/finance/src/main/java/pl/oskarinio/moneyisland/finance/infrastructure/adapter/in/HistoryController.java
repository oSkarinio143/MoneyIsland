package pl.oskarinio.moneyisland.finance.infrastructure.adapter.in;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarinio.moneyisland.finance.application.port.history.LoadExpenseDataUseCase;
import pl.oskarinio.moneyisland.finance.application.port.history.LoadIncomeDataUseCase;
import pl.oskarinio.moneyisland.finance.application.port.history.SaveHistoryExpenseUseCase;
import pl.oskarinio.moneyisland.finance.application.port.history.SaveHistoryIncomeUseCase;
import pl.oskarinio.moneyisland.shared.config.Route;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(Route.MAIN + Route.USER + Route.FINANCE + Route.HISTORY)
public class HistoryController {
    private final SaveHistoryIncomeUseCase saveHistoryIncomeUseCase;
    private final SaveHistoryExpenseUseCase saveHistoryExpenseUseCase;
    private final LoadIncomeDataUseCase loadIncomeDataUseCase;
    private final LoadExpenseDataUseCase loadExpenseDataUseCase;
    private static final String USERNAME = "username";
    private static final String MONTHLY_VALUE = "monthlyValues";
    private static final String ROUTE_HISTORY = Route.REDIRECT + Route.USER + Route.FINANCE + Route.HISTORY;

    public HistoryController(SaveHistoryIncomeUseCase saveHistoryIncomeUseCase, SaveHistoryExpenseUseCase saveHistoryExpenseUseCase, LoadIncomeDataUseCase loadIncomeDataUseCase, LoadExpenseDataUseCase loadExpenseDataUseCase) {
        this.saveHistoryIncomeUseCase = saveHistoryIncomeUseCase;
        this.saveHistoryExpenseUseCase = saveHistoryExpenseUseCase;
        this.loadIncomeDataUseCase = loadIncomeDataUseCase;
        this.loadExpenseDataUseCase = loadExpenseDataUseCase;
    }

    @GetMapping()
    public String displayBalancePanel(Model model,
                                      @RequestParam(USERNAME) String username){
        model.addAttribute("incomeData", loadIncomeDataUseCase.loadIncomeData(username));
        model.addAttribute("expenseData", loadExpenseDataUseCase.loadExpenseData(username));
        return Route.VIEW_HISTORY;
    }

    @Transactional
    @PostMapping(Route.INCOME)
    public String saveIncome(@RequestParam(MONTHLY_VALUE) List<BigDecimal> monthlyValues,
                             @RequestParam(USERNAME) String username,
                             RedirectAttributes redirectAttributes){
        saveHistoryIncomeUseCase.saveHistoryIncomeData(monthlyValues,username);
        redirectAttributes.addAttribute(USERNAME, username);
        return ROUTE_HISTORY;
    }

    @PostMapping(Route.EXPENSE)
    public String saveExpense(@RequestParam(MONTHLY_VALUE) List<BigDecimal> monthlyValues,
                              @RequestParam(USERNAME) String username,
                              RedirectAttributes redirectAttributes){
        saveHistoryExpenseUseCase.saveHistoryExpenseData(monthlyValues,username);
        redirectAttributes.addAttribute(USERNAME, username);
        return ROUTE_HISTORY;
    }
}
