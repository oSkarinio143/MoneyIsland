package pl.oskarinio.moneyisland.finance.historyBlock.repository.Income;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskarinio.moneyisland.finance.historyBlock.entity.HistoryIncomeEntity;

public interface HistoryIncomeRepositoryJpa extends JpaRepository<HistoryIncomeEntity, Long> {
    HistoryIncomeEntity findByUsername(String username);
}
