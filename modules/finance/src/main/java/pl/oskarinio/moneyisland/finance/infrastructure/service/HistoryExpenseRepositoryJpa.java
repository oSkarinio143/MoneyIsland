package pl.oskarinio.moneyisland.finance.infrastructure.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.HistoryExpenseEntity;

public interface HistoryExpenseRepositoryJpa extends JpaRepository<HistoryExpenseEntity, Long> {
    HistoryExpenseEntity findByUsername(String username);
}
