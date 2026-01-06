package pl.oskarinio.moneyisland.finance.infrastructure.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.TargetEntity;

public interface TargetRepositoryJpa extends JpaRepository<TargetEntity, Long> {
    TargetEntity findByUsername(String username);
}
