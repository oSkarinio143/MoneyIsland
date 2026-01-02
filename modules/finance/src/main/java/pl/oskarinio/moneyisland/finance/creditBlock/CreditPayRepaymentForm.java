package pl.oskarinio.moneyisland.finance.creditBlock;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreditPayRepaymentForm {
    private String username;
    private String creditName;
    private BigDecimal quantity;
}
