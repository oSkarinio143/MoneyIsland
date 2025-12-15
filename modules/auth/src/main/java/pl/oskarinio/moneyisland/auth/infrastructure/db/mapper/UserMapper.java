package pl.oskarinio.moneyisland.auth.infrastructure.db.mapper;


import pl.oskarinio.moneyisland.auth.infrastructure.db.entity.UserEntity;
import pl.oskarinio.moneyisland.shared.uncategorized.User;

public class UserMapper {
    private UserMapper(){}

    public static User toDomain(UserEntity userEntity){
        User user = new User(userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getRegistrationDate());
        user.setId(userEntity.getId());
        user.setRoles(userEntity.getRoles());
        if(userEntity.getRefreshToken() != null) {
            user.setRefreshToken(RefreshTokenMapper.toDomain(userEntity.getRefreshToken()));
        }
        return user;
    }

    public static UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity(user.getUsername(), user.getEmail(), user.getPassword(), user.getRegistrationDate());
        userEntity.setId(user.getId());
        if(user.getRefreshToken() != null) {
            userEntity.setRefreshToken(RefreshTokenMapper.toEntity(user.getRefreshToken()));
        }
        if(!user.getRoles().isEmpty())
            userEntity.setRoles(user.getRoles());
        return userEntity;
    }
}
