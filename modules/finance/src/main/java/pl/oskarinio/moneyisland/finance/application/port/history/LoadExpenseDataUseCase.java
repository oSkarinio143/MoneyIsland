package pl.oskarinio.moneyisland.finance.application.port.history;

import java.math.BigDecimal;
import java.util.List;

public interface LoadExpenseDataUseCase {
    List<BigDecimal> loadExpenseData(String username);
}
