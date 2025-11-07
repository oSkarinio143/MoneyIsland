package pl.oskarinio.moneyisland.shared.uncategorized;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface RefreshTokenRepositoryUseCase extends JpaRepository<RefreshTokenEntity, Integer> {
    @Modifying
    @Query("DELETE FROM RefreshTokenEntity t WHERE t.expirationDate < :date")
    void deleteExpiredToken(@Param("date") Instant date);
}
