package pl.oskarinio.moneyisland.finance.BalanceBlock;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import java.math.BigDecimal;

@Entity
@Table(name = "asset_user")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Unique
    private String assetName;

    @NonNull
    private BigDecimal assetValue;

    @NonNull
    private String username;
}
