package pl.oskarinio.moneyisland.finance.infrastructure.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(
        name = "user_asset",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"asset_name", "username"})
        })
@Data
@NoArgsConstructor
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name = "asset_name")
    private String assetName;
    private BigDecimal assetValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
