package pl.oskarinio.moneyisland.finance.domain.dto.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreditAddForm {
    @NonNull
    private String username;
    private String creditName;
    private double percent;
    private BigDecimal months;
    private BigDecimal value;
    private BigDecimal finalValue;
    private BigDecimal repayment;
}
