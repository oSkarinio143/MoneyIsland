package pl.oskarinio.moneyisland.finance.domain.port;

import pl.oskarinio.moneyisland.finance.domain.dto.HistoryExpense;

public interface HistoryExpenseRepository {
    HistoryExpense findByUsername(String username);
    void save(HistoryExpense historyExpense);
}
