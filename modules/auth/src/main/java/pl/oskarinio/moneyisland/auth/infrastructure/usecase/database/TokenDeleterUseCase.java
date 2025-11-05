package pl.oskarinio.moneyisland.auth.infrastructure.usecase.database;


import pl.oskarinio.moneyisland.auth.infrastructure.port.database.RefreshTokenRepository;

import java.time.Clock;
import java.time.Instant;

//Czysci wygasłe RefreshTokeny co 24 godziny
public class TokenDeleterUseCase {
    private final RefreshTokenRepository refreshTokenRepository;
    private final Clock clock;

    public TokenDeleterUseCase(RefreshTokenRepository refreshTokenRepository, Clock clock) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.clock = clock;
    }

    public void cleanExpiredTokens() {
        refreshTokenRepository.deleteExpiredToken(Instant.now(clock));
    }
}
