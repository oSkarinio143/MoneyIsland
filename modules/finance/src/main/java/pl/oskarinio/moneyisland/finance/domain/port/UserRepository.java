package pl.oskarinio.moneyisland.finance.domain.port;

import pl.oskarinio.moneyisland.finance.domain.dto.User;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.UserEntity;

public interface UserRepository {
    void save(User user);
    void delete(String username);
    UserEntity getUserEntity(long id);
    User findByUsername(String username);
    User findByUserId(long id);
    UserEntity findUserEntityByUserId(long id);
}
