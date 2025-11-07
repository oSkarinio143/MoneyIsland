package pl.oskarinio.moneyisland.shared.uncategorized;

import java.time.Instant;

public interface RefreshTokenRepository {
    void deleteExpiredToken(Instant date);
}
