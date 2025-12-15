package pl.oskarinio.moneyisland.refresh.uncategorized;

import java.time.Instant;

public interface RefreshTokenRepository {
    void deleteExpiredToken(Instant date);
}
