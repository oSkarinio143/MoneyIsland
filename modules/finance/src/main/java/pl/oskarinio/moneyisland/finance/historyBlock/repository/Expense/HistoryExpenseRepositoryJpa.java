package pl.oskarinio.moneyisland.finance.historyBlock.repository.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.oskarinio.moneyisland.finance.historyBlock.entity.HistoryExpenseEntity;

public interface HistoryExpenseRepositoryJpa extends JpaRepository<HistoryExpenseEntity, Long> {
    HistoryExpenseEntity findByUsername(String username);
}
