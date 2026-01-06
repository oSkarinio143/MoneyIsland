package pl.oskarinio.moneyisland.finance.infrastructure.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.AssetEntity;

import java.util.List;

public interface AssetRepositoryJpa extends JpaRepository<AssetEntity, Long> {
    List<AssetEntity> findByUsername(String username);
}
