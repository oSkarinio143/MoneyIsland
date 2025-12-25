package pl.oskarinio.moneyisland.finance.BalanceBlock.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetOperationFormRequest {
    private String username;
    private Long assetId;
    private BigDecimal assetValue;
}
