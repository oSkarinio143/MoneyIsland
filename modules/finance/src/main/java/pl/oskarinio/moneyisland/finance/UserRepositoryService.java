package pl.oskarinio.moneyisland.finance;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryService implements UserRepository {
    private final UserRepositoryUseCase userRepositoryUseCase;

    public UserRepositoryService(UserRepositoryUseCase userRepositoryUseCase) {
        this.userRepositoryUseCase = userRepositoryUseCase;
    }

    public UserEntity getUser(long id) {
        return userRepositoryUseCase.getReferenceById(id);
    }

    public Optional<UserEntity> findByUsername(String username){
        return userRepositoryUseCase.findByUsername(username);
    }

    public Optional<UserEntity> findByAuthUserId(long id){
        return userRepositoryUseCase.findByAuthUserId(id);
    }
}
