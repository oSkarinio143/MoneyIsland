package pl.oskarinio.moneyisland.finance.historyBlock.service;

import java.math.BigDecimal;
import java.util.List;

public interface LoadIncomeDataUseCase {
    List<BigDecimal> loadIncomeData(String username);
}
