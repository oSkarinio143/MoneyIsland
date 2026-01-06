package pl.oskarinio.moneyisland.finance.domain.dto;

import lombok.Data;
import lombok.NonNull;

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
