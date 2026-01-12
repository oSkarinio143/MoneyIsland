package pl.oskarinio.moneyisland.auth.infrastructure.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.oskarinio.moneyisland.auth.infrastructure.service.repo.RefreshTokenRepositoryJpa;

import java.time.Clock;
import java.time.Instant;

@Component
@Slf4j
public class TokenDeleter {
    private final RefreshTokenRepositoryJpa refreshTokenRepository;
    private final Clock clock;

    public TokenDeleter(RefreshTokenRepositoryJpa refreshTokenRepositoryJpa, Clock clock) {
        this.refreshTokenRepository = refreshTokenRepositoryJpa;
        this.clock = clock;
    }

    @Transactional
    @Scheduled(cron = "0 0 8 * * ?")
    public void cleanExpiredTokens() {
        log.debug("Uruchamiam czyszczenie tokenow");
        refreshTokenRepository.deleteExpiredToken(Instant.now(clock));
    }
}
