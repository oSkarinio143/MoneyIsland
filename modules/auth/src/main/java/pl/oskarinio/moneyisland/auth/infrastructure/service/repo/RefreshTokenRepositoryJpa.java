package pl.oskarinio.moneyisland.auth.infrastructure.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.oskarinio.moneyisland.auth.infrastructure.db.entity.RefreshTokenEntity;

import java.time.Instant;

public interface RefreshTokenRepositoryJpa extends JpaRepository<RefreshTokenEntity, Integer> {
    @Modifying
    @Query("DELETE FROM RefreshTokenEntity t WHERE t.expirationDate < :date")
    void deleteExpiredToken(@Param("date") Instant date);
}
