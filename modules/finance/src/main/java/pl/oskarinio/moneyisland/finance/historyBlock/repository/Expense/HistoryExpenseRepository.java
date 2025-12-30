package pl.oskarinio.moneyisland.finance.historyBlock.repository.Expense;

import pl.oskarinio.moneyisland.finance.historyBlock.Dto.HistoryExpense;

public interface HistoryExpenseRepository {
    HistoryExpense findByUsername(String username);
    void save(HistoryExpense historyExpense);
}
