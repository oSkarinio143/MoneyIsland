package pl.oskarinio.moneyisland.finance.GoalBlock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTargetsRepositoryJpa extends JpaRepository<UserTargetsEntity, Long> {
    UserTargetsEntity findByUsername(String username);
}
