package pl.oskarinio.moneyisland.finance.application.service;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.application.port.history.LoadExpenseDataUseCase;
import pl.oskarinio.moneyisland.finance.application.port.history.LoadIncomeDataUseCase;
import pl.oskarinio.moneyisland.finance.application.port.history.SaveHistoryExpenseUseCase;
import pl.oskarinio.moneyisland.finance.application.port.history.SaveHistoryIncomeUseCase;
import pl.oskarinio.moneyisland.finance.domain.port.UserRepository;
import pl.oskarinio.moneyisland.finance.domain.port.HistoryExpenseRepository;
import pl.oskarinio.moneyisland.finance.domain.port.HistoryIncomeRepository;
import pl.oskarinio.moneyisland.finance.domain.service.HistoryBalanceDomainService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HistoryBalanceService implements SaveHistoryExpenseUseCase, SaveHistoryIncomeUseCase, LoadExpenseDataUseCase, LoadIncomeDataUseCase {
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
