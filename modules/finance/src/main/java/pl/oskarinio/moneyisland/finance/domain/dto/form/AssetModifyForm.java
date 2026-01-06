package pl.oskarinio.moneyisland.finance.domain.dto.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetModifyForm {
    private String username;
    private Long assetId;
    private BigDecimal assetValue;
}
