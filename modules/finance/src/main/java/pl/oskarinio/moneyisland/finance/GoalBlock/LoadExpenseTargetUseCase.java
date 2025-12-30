package pl.oskarinio.moneyisland.finance.GoalBlock;

import java.math.BigDecimal;

public interface LoadExpenseTargetUseCase {
    BigDecimal loadExpenseTarget(String username);
}
