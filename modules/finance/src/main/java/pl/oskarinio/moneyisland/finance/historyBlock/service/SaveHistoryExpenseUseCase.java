package pl.oskarinio.moneyisland.finance.historyBlock.service;

import java.math.BigDecimal;
import java.util.List;

public interface SaveHistoryExpenseUseCase {
    void saveHistoryExpenseData(List<BigDecimal> monthlyValues, String username);
}
