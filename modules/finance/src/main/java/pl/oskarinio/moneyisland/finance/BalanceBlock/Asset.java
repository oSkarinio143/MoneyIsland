package pl.oskarinio.moneyisland.finance.BalanceBlock;

import lombok.Data;
import lombok.NonNull;
import pl.oskarinio.moneyisland.finance.User;

import java.math.BigDecimal;

@Data
public class Asset {
    private Long id;
    @NonNull
    private String assetName;
    @NonNull
    private BigDecimal assetValue;
    @NonNull
    private String username;

    private User user;
}
