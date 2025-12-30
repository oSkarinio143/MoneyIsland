package pl.oskarinio.moneyisland.finance.GoalBlock;

import java.math.BigDecimal;

public interface LoadIncomeTargetUseCase {
    BigDecimal loadIncomeTarget(String username);
}
