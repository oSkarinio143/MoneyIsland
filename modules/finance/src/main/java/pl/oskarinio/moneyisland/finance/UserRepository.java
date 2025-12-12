package pl.oskarinio.moneyisland.finance;

import java.util.Optional;

public interface UserRepository {
    UserEntity getUser(long id);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByAuthUserId(long id);
}
