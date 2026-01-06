package pl.oskarinio.moneyisland.finance.application.port.target;

import java.math.BigDecimal;

public interface LoadExpenseTargetUseCase {
    BigDecimal loadExpenseTarget(String username);
}
