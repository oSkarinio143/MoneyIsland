package pl.oskarinio.moneyisland.finance.GoalBlock;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoalFormRequest {
    private String username;
    private BigDecimal targetIncome;
    private BigDecimal targetExpense;
}
