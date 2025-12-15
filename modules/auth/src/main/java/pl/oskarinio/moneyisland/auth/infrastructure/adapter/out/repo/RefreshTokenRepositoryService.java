package pl.oskarinio.moneyisland.auth.infrastructure.adapter.out.repo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.oskarinio.moneyisland.auth.infrastructure.service.repo.RefreshTokenRepositoryJpa;

import java.time.Instant;

@Slf4j
@Component
public class RefreshTokenRepositoryService  {
    private final RefreshTokenRepositoryJpa refreshTokenRepostiory;

    public RefreshTokenRepositoryService(RefreshTokenRepositoryJpa jpaRepository) {
        this.refreshTokenRepostiory = jpaRepository;
    }

    public void deleteExpiredToken(Instant date) {
        log.debug("Usuwam wygasłe tokeny");
        refreshTokenRepostiory.deleteExpiredToken(date);
    }
}
