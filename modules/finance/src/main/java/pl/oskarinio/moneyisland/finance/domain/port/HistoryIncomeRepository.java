package pl.oskarinio.moneyisland.finance.domain.port;

import pl.oskarinio.moneyisland.finance.domain.dto.HistoryIncome;

public interface HistoryIncomeRepository {
    HistoryIncome findByUsername(String username);
    void save(HistoryIncome historyIncome);
}
