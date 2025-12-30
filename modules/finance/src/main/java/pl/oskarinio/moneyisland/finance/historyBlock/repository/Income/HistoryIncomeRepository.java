package pl.oskarinio.moneyisland.finance.historyBlock.repository.Income;

import pl.oskarinio.moneyisland.finance.historyBlock.Dto.HistoryIncome;

public interface HistoryIncomeRepository {
    HistoryIncome findByUsername(String username);
    void save(HistoryIncome historyIncome);
}
