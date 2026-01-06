package pl.oskarinio.moneyisland.finance.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Credit {
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String creditName;
    @NonNull
    private double percent;
    @NonNull
    private BigDecimal months;
    @NonNull
    private BigDecimal value;
    @NonNull
    private BigDecimal finalValue;
    @NonNull
    private BigDecimal payedValue;
    @NonNull
    private BigDecimal notPayedValue;
    @NonNull
    private BigDecimal repayment;
    @NonNull
    private BigDecimal repaymentQuantity;

    private User user;
}
