package pl.oskarinio.moneyisland.finance.infrastructure.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.HistoryIncomeEntity;

public interface HistoryIncomeRepositoryJpa extends JpaRepository<HistoryIncomeEntity, Long> {
    HistoryIncomeEntity findByUsername(String username);
}
