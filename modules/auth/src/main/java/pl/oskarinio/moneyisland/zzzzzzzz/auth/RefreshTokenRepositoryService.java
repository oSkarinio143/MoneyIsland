package pl.oskarinio.moneyisland.zzzzzzzz.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.oskarinio.moneyisland.auth.RefreshTokenRepositoryUseCase;

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
