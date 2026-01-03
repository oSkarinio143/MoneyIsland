package pl.oskarinio.moneyisland.finance.GoalBlock;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TargetRequest {
    private String username;
    private BigDecimal targetIncome;
    private BigDecimal targetExpense;
}
