package pl.oskarinio.moneyisland.finance.infrastructure.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.CreditEntity;

import java.util.List;

public interface CreditRepositoryJpa extends JpaRepository<CreditEntity, Long> {
        List<CreditEntity> findCreditsByUsername(String username);
}
