package pl.oskarinio.moneyisland.refresh.uncategorized;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
public class RefreshTokenRepositoryService implements RefreshTokenRepository {
    private final RefreshTokenRepositoryUseCase refreshTokenRepostiory;

    public RefreshTokenRepositoryService(RefreshTokenRepositoryUseCase jpaRepository) {
        this.refreshTokenRepostiory = jpaRepository;
    }

    @Override
    public void deleteExpiredToken(Instant date) {
        log.debug("Usuwam wygasłe tokeny");
        refreshTokenRepostiory.deleteExpiredToken(date);
    }
}
