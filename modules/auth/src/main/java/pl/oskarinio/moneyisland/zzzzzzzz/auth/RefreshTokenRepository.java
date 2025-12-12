package pl.oskarinio.moneyisland.zzzzzzzz.auth;

import java.time.Instant;

public interface RefreshTokenRepository {
    void deleteExpiredToken(Instant date);
}
