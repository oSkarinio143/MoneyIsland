package pl.oskarinio.moneyisland.finance.application.port.history;

import java.math.BigDecimal;
import java.util.List;

public interface SaveHistoryIncomeUseCase {
    void saveHistoryIncomeData(List<BigDecimal> monthlyValues, String username);
}
