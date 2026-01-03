package pl.oskarinio.moneyisland.finance;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.map.MapStruct;
import pl.oskarinio.moneyisland.shared.domain.exception.UsernameNotFoundException;

import java.util.Optional;

@Service
public class UserRepositoryService implements UserRepository {
    private final UserRepositoryJpa userRepositoryJpa;
    private final MapStruct mapper;

    public UserRepositoryService(UserRepositoryJpa userRepositoryJpa, MapStruct mapper) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = mapper.toUserEntity(user);
        userRepositoryJpa.save(userEntity);
    }

    @Override
    public void delete(String username) {
        Optional<UserEntity> userEntityOptional = userRepositoryJpa.findByUsername(username);
        if(userEntityOptional.isPresent())
            userRepositoryJpa.delete(userEntityOptional.get());
        else
            throw new UsernameNotFoundException();
    }

    public UserEntity getUserEntity(long id) {
        return userRepositoryJpa.getReferenceById(id);
    }

    public User findByUsername(String username){
        Optional<UserEntity> userEntityOptional = userRepositoryJpa.findByUsername(username);
        if(userEntityOptional.isPresent())
            return mapper.toUser(userEntityOptional.get());
        throw new UsernameNotFoundException();
    }

    public User findByUserId(long id){
        Optional<UserEntity> userEntityOptional = userRepositoryJpa.findById(id);
        if(userEntityOptional.isPresent())
            return mapper.toUser(userEntityOptional.get());
        throw new UsernameNotFoundException();
    }

    public UserEntity findUserEntityByUserId(long id){
        Optional<UserEntity> userEntityOptional = userRepositoryJpa.findById(id);
        if(userEntityOptional.isPresent())
            return userEntityOptional.get();
        throw new UsernameNotFoundException();
    }
}
