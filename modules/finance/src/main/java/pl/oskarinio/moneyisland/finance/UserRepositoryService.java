package pl.oskarinio.moneyisland.finance;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.BalanceBlock.AssetRepo.User;
import pl.oskarinio.moneyisland.MapStruct;

import java.util.Optional;

@Service
public class UserRepositoryService implements UserRepository {
    private final UserRepositoryJpa userRepositoryUseCase;
    private final MapStruct mapper;

    public UserRepositoryService(UserRepositoryJpa userRepositoryUseCase, MapStruct mapper) {
        this.userRepositoryUseCase = userRepositoryUseCase;
        this.mapper = mapper;
    }

    public UserEntity getUserEntity(long id) {
        return userRepositoryUseCase.getReferenceById(id);
    }

    public Optional<UserEntity> findByUsername(String username){
        return userRepositoryUseCase.findByUsername(username);
    }

    public Optional<UserEntity> findByAuthUserId(long id){
        return userRepositoryUseCase.findByAuthUserId(id);
    }

    public UserEntity userToUserEntity(User user){
        return mapper.toUserEntity(user);
    }

    public User userEntityToUser(UserEntity userEntity){
        return mapper.toUser(userEntity);
    }
}
