package pl.oskarinio.moneyisland.finance.application.port.history;

import java.math.BigDecimal;
import java.util.List;

public interface LoadIncomeDataUseCase {
    List<BigDecimal> loadIncomeData(String username);
}
