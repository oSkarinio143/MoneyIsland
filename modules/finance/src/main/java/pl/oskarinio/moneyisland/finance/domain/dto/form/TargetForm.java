package pl.oskarinio.moneyisland.finance.domain.dto.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TargetForm {
    private String username;
    private BigDecimal targetIncome;
    private BigDecimal targetExpense;
}
