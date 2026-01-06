package pl.oskarinio.moneyisland.finance.domain.dto.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetAddForm {
    private String assetName;
    private BigDecimal assetValue;
    private String username;
}
