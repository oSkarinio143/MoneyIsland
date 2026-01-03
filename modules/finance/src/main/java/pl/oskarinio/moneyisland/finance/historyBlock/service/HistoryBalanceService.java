package pl.oskarinio.moneyisland.finance.historyBlock.service;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.UserRepository;
import pl.oskarinio.moneyisland.finance.historyBlock.repository.Expense.HistoryExpenseRepository;
import pl.oskarinio.moneyisland.finance.historyBlock.repository.Income.HistoryIncomeRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HistoryBalanceService implements SaveHistoryExpenseUseCase, SaveHistoryIncomeUseCase, LoadExpenseDataUseCase, LoadIncomeDataUseCase{
    private final HistoryBalanceDomainService historyBalanceDomainService;

    public HistoryBalanceService(HistoryExpenseRepository historyExpenseRepository,
                                 HistoryIncomeRepository historyIncomeRepository,
                                 UserRepository userRepository) {
        this.historyBalanceDomainService = new HistoryBalanceDomainService(historyExpenseRepository, historyIncomeRepository, userRepository);
    }

    @Override
    public void saveHistoryExpenseData(List<BigDecimal> monthlyValues, String username) {
        historyBalanceDomainService.saveHistoryExpenseData(monthlyValues,username);
    }

    @Override
    public void saveHistoryIncomeData(List<BigDecimal> monthlyValues, String username) {
        historyBalanceDomainService.saveHistoryIncomeData(monthlyValues,username);
    }

    @Override
    public List<BigDecimal> loadExpenseData(String username) {
        return historyBalanceDomainService.loadExpenseData(username);
    }

    @Override
    public List<BigDecimal> loadIncomeData(String username) {
        return historyBalanceDomainService.loadIncomeData(username);
    }
}
