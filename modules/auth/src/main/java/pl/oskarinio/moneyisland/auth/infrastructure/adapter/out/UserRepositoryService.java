package pl.oskarinio.moneyisland.auth.infrastructure.adapter.out;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.oskarinio.moneyisland.auth.domain.port.UserRepository;
import pl.oskarinio.moneyisland.auth.infrastructure.db.entity.UserEntity;
import pl.oskarinio.moneyisland.auth.infrastructure.db.mapper.UserMapper;
import pl.oskarinio.moneyisland.shared.uncategorized.User;
import pl.oskarinio.moneyisland.auth.infrastructure.service.UserRepositoryJpa;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
public class UserRepositoryService implements UserRepository {
    private final UserRepositoryJpa userRepositoryJpa;

    public UserRepositoryService(UserRepositoryJpa userRepositoryJpaJpa) {
        this.userRepositoryJpa = userRepositoryJpaJpa;
    }

    @Override
    @Transactional
    public Optional<User> findByUsername(String username) {
        log.debug("Szukam w bazie uzytkownika. Nazwa = {}", username);
        return userRepositoryJpa.findByUsername(username).map(UserMapper::toDomain);
    }

    @Override
    public void removeRefreshTokenRelation(Instant date) {
        log.debug("Usuwam relacje wygasnietych refreshTokenow z Uzytkownikami");
        userRepositoryJpa.removeRefreshTokenRelation(date);
    }

    @Override
    public Long count() {
        long amountUser = userRepositoryJpa.count();
        log.debug("Licze uzytkownikow. Wynik = {}", amountUser);
        return amountUser;
    }

    @Override
    public void save(User user) {
        log.debug("Zapisuje / Aktualizuje uzytkownika w bazie. Nazwa = {}", user.getUsername());
        userRepositoryJpa.save(UserMapper.toEntity(user));
    }

    @Override
    public void delete(User user) {
        log.debug("Usuwam uzytkownika z bazy. Nazwa = {}", user.getUsername());
        userRepositoryJpa.delete(UserMapper.toEntity(user));
    }

    @Override
    @Transactional
    public List<User> findAll() {
        List<UserEntity> userEntities = userRepositoryJpa.findAll();
        Function<UserEntity, User> userMapper = UserMapper::toDomain;
        return userEntities.stream().map(userMapper).toList();
    }
}
