package pl.oskarinio.moneyisland.finance.creditBlock.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskarinio.moneyisland.finance.creditBlock.CreditEntity;

import java.util.List;

public interface CreditRepositoryJpa extends JpaRepository<CreditEntity, Long> {
        List<CreditEntity> findCreditsByUsername(String username);
}
