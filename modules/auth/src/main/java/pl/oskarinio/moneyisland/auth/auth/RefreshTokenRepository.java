package pl.oskarinio.moneyisland.auth.auth;

import java.time.Instant;

public interface RefreshTokenRepository {
    void deleteExpiredToken(Instant date);
}
