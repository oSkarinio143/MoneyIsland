package pl.oskarinio.moneyisland.finance.BalanceBlock.AssetRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskarinio.moneyisland.finance.BalanceBlock.AssetEntity;

import java.util.List;

public interface AssetRepositoryJpa extends JpaRepository<AssetEntity, Long> {
    List<AssetEntity> findByUsername(String username);
}
