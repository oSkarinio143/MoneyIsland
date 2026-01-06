package pl.oskarinio.moneyisland.finance.application.port.target;

import java.math.BigDecimal;

public interface LoadIncomeTargetUseCase {
    BigDecimal loadIncomeTarget(String username);
}
