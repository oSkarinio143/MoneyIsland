package pl.oskarinio.moneyisland.finance.domain.dto.form;

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
