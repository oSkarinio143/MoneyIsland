package pl.oskarinio.moneyisland.finance.GoalBlock;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserTargets {
    private Long id;
    @NonNull
    private String username;
    private BigDecimal incomeTarget;
    private BigDecimal expenseTarget;
}
