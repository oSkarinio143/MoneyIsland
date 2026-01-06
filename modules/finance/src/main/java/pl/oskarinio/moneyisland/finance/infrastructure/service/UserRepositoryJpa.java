package pl.oskarinio.moneyisland.finance.infrastructure.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.UserEntity;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUsername(@Param("username") String username);
    Optional<UserEntity> findByAuthUserId(@Param("authUserId") Long authUserId);
}
