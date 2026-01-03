package pl.oskarinio.moneyisland.finance;

public interface UserRepository {
    void save(User user);
    void delete(String username);
    UserEntity getUserEntity(long id);
    User findByUsername(String username);
    User findByUserId(long id);
    UserEntity findUserEntityByUserId(long id);
}
