package pl.oskarinio.moneyisland.finance.historyBlock.service;

import pl.oskarinio.moneyisland.finance.historyBlock.Dto.HistoryExpense;
import pl.oskarinio.moneyisland.finance.historyBlock.Dto.HistoryIncome;
import pl.oskarinio.moneyisland.finance.historyBlock.Month;
import pl.oskarinio.moneyisland.finance.historyBlock.repository.Expense.HistoryExpenseRepository;
import pl.oskarinio.moneyisland.finance.historyBlock.repository.Income.HistoryIncomeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryBalanceDomainService {
    private final HistoryExpenseRepository historyExpenseRepository;
    private final HistoryIncomeRepository historyIncomeRepository;

    public HistoryBalanceDomainService(HistoryExpenseRepository historyExpenseRepository, HistoryIncomeRepository historyIncomeRepository) {
        this.historyExpenseRepository = historyExpenseRepository;
        this.historyIncomeRepository = historyIncomeRepository;
    }

    public void saveHistoryIncomeData(List<BigDecimal> monthlyValues, String username){
        System.out.println("wartosci z kontrolera - " + monthlyValues);
        HistoryIncome historyIncome = loadHistoryIncome(username);
        System.out.println("wartosci z hisinc - " + historyIncome.getMonthValue());
        historyIncome.addValuesToMonths(monthlyValues);
        System.out.println("wartosci z kontrolera w hisinc - " + historyIncome.getUsername() + ", " + historyIncome.getMonthValue());
        historyIncomeRepository.save(historyIncome);
    }

    public void saveHistoryExpenseData(List<BigDecimal> monthlyValues, String username){
        HistoryExpense historyExpense = loadHistoryExpense(username);
        historyExpense.addValuesToMonths(monthlyValues);
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
