package pl.oskarinio.moneyisland.finance.BalanceBlock.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetFormRequest {
    private String assetName;
    private BigDecimal assetValue;
    private String username;
}
