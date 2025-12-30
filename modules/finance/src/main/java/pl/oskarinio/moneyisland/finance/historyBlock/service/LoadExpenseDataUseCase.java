package pl.oskarinio.moneyisland.finance.historyBlock.service;

import java.math.BigDecimal;
import java.util.List;

public interface LoadExpenseDataUseCase {
    List<BigDecimal> loadExpenseData(String username);
}
