package pl.oskarinio.moneyisland.auth.domain.port;


import pl.oskarinio.moneyisland.shared.uncategorized.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    void removeRefreshTokenRelation(Instant date);
    Long count();
    void save(User user);
    void delete(User user);
    List<User> findAll();
}
