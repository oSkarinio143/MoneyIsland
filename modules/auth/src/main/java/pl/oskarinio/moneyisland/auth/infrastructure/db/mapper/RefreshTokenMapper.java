package pl.oskarinio.moneyisland.auth.infrastructure.db.mapper;


import pl.oskarinio.moneyisland.auth.infrastructure.db.entity.RefreshTokenEntity;
import pl.oskarinio.moneyisland.shared.uncategorized.RefreshToken;

class RefreshTokenMapper {
    private RefreshTokenMapper(){}
    static RefreshToken toDomain(RefreshTokenEntity refreshTokenEntity){
        RefreshToken refreshToken = new RefreshToken(refreshTokenEntity.getTokenHash(),
                refreshTokenEntity.getCreationDate(),
                refreshTokenEntity.getExpirationDate());
        refreshToken.setId(refreshTokenEntity.getId());
        return refreshToken;
    }

    static RefreshTokenEntity toEntity(RefreshToken refreshToken){
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity(refreshToken.getTokenHash(),
                refreshToken.getCreationDate(),
                refreshToken.getExpirationDate());
        refreshTokenEntity.setId(refreshToken.getId());
        return refreshTokenEntity;
    }
}
