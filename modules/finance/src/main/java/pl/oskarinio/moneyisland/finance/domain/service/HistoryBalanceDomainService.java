package pl.oskarinio.moneyisland.finance.domain.service;

import pl.oskarinio.moneyisland.finance.domain.port.UserRepository;
import pl.oskarinio.moneyisland.finance.domain.dto.HistoryExpense;
import pl.oskarinio.moneyisland.finance.domain.dto.HistoryIncome;
import pl.oskarinio.moneyisland.finance.domain.dto.Month;
import pl.oskarinio.moneyisland.finance.domain.port.HistoryExpenseRepository;
import pl.oskarinio.moneyisland.finance.domain.port.HistoryIncomeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryBalanceDomainService {
    private final HistoryExpenseRepository historyExpenseRepository;
    private final HistoryIncomeRepository historyIncomeRepository;
    private final UserRepository userRepository;

    public HistoryBalanceDomainService(HistoryExpenseRepository historyExpenseRepository, HistoryIncomeRepository historyIncomeRepository, UserRepository userRepository) {
        this.historyExpenseRepository = historyExpenseRepository;
        this.historyIncomeRepository = historyIncomeRepository;
        this.userRepository = userRepository;
    }

    public void saveHistoryIncomeData(List<BigDecimal> monthlyValues, String username){
        HistoryIncome historyIncome = loadHistoryIncome(username);
        historyIncome.addValuesToMonths(monthlyValues);
        historyIncome.setUser(userRepository.findByUsername(username));
        historyIncomeRepository.save(historyIncome);
    }

    public void saveHistoryExpenseData(List<BigDecimal> monthlyValues, String username){
        HistoryExpense historyExpense = loadHistoryExpense(username);
        historyExpense.addValuesToMonths(monthlyValues);
        historyExpense.setUser(userRepository.findByUsername(username));
        historyExpenseRepository.save(historyExpense);
    }

    public List<BigDecimal> loadIncomeData(String username){
        HistoryIncome historyIncome = historyIncomeRepository.findByUsername(username);
        return createList(historyIncome);
    }

    public List<BigDecimal> loadExpenseData(String username){
        HistoryExpense historyExpense = historyExpenseRepository.findByUsername(username);
        return createList(historyExpense);
    }

    private HistoryIncome loadHistoryIncome(String username){
        if(historyIncomeRepository.findByUsername(username) != null)
            return historyIncomeRepository.findByUsername(username);
        return new HistoryIncome(username);
    }

    private HistoryExpense loadHistoryExpense(String username){
        if(historyExpenseRepository.findByUsername(username) != null)
            return historyExpenseRepository.findByUsername(username);
        return new HistoryExpense(username);
    }

    private List<BigDecimal> createList(HistoryIncome historyIncome){
        if(historyIncome == null)
            return createZeroList();
        else
            return createValuesList(historyIncome.getMonthValue());
    }


    private List<BigDecimal> createList(HistoryExpense historyExpense){
        if(historyExpense == null)
            return createZeroList();
        else
            return createValuesList(historyExpense.getMonthValue());
    }

    private List<BigDecimal> createZeroList(){
        List<BigDecimal> valuesZero = new ArrayList();
        valuesZero.addAll(List.of(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
        return valuesZero;
    }

    private List<BigDecimal> createValuesList(Map<Month, BigDecimal> monthValue){
        List<BigDecimal> valuesZero = new ArrayList();
        monthValue.forEach((k, v) -> valuesZero.add(v));
        return valuesZero;
    }
}
