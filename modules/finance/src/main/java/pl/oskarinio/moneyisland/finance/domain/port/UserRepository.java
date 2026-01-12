package pl.oskarinio.moneyisland.finance.domain.port;

import pl.oskarinio.moneyisland.finance.domain.dto.User;

public interface UserRepository {
    void save(User user);
    void delete(String username);
    User findByUsername(String username);
    User findByUserId(long id);
}
