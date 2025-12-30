package pl.oskarinio.moneyisland.finance.historyBlock;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.oskarinio.moneyisland.finance.historyBlock.service.LoadExpenseDataUseCase;
import pl.oskarinio.moneyisland.finance.historyBlock.service.LoadIncomeDataUseCase;
import pl.oskarinio.moneyisland.finance.historyBlock.service.SaveHistoryExpenseUseCase;
import pl.oskarinio.moneyisland.finance.historyBlock.service.SaveHistoryIncomeUseCase;
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

    public HistoryController(SaveHistoryIncomeUseCase saveHistoryIncomeUseCase, SaveHistoryExpenseUseCase saveHistoryExpenseUseCase, LoadIncomeDataUseCase loadIncomeDataUseCase, LoadExpenseDataUseCase loadExpenseDataUseCase) {
        this.saveHistoryIncomeUseCase = saveHistoryIncomeUseCase;
        this.saveHistoryExpenseUseCase = saveHistoryExpenseUseCase;
        this.loadIncomeDataUseCase = loadIncomeDataUseCase;
        this.loadExpenseDataUseCase = loadExpenseDataUseCase;
    }

    @GetMapping()
    public String displayBalancePanel(Model model,
                                      @RequestParam("username") String username){
        model.addAttribute("incomeData", loadIncomeDataUseCase.loadIncomeData(username));
        model.addAttribute("expenseData", loadExpenseDataUseCase.loadExpenseData(username));
        return Route.VIEW_HISTORY;
    }

    @Transactional
    @PostMapping("/income")
    public String saveIncome(@RequestParam("monthlyValues") List<BigDecimal> monthlyValues,
                             @RequestParam("username") String username,
                             RedirectAttributes redirectAttributes){
        saveHistoryIncomeUseCase.saveHistoryIncomeData(monthlyValues,username);
        redirectAttributes.addAttribute("username", username);
        return  Route.REDIRECT + Route.USER + Route.FINANCE + Route.HISTORY;
    }

    @PostMapping("/expense")
    public String saveExpense(@RequestParam("monthlyValues") List<BigDecimal> monthlyValues,
                              @RequestParam("username") String username,
                              RedirectAttributes redirectAttributes){
        saveHistoryExpenseUseCase.saveHistoryExpenseData(monthlyValues,username);
        redirectAttributes.addAttribute("username", username);
        return Route.REDIRECT + Route.USER + Route.FINANCE + Route.HISTORY;
    }
}
