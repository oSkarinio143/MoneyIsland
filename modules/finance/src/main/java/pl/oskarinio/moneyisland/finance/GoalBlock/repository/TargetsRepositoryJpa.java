package pl.oskarinio.moneyisland.finance.GoalBlock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetsRepositoryJpa extends JpaRepository<TargetEntity, Long> {
    TargetEntity findByUsername(String username);
}
