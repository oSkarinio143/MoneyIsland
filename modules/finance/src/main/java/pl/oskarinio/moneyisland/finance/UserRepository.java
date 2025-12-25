package pl.oskarinio.moneyisland.finance;

import pl.oskarinio.moneyisland.finance.BalanceBlock.AssetRepo.User;

import java.util.Optional;

public interface UserRepository {
    UserEntity getUserEntity(long id);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByAuthUserId(long id);
    UserEntity userToUserEntity(User user);
    User userEntityToUser(UserEntity userEntity);
}
