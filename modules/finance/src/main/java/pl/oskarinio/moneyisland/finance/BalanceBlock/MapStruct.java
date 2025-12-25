package pl.oskarinio.moneyisland.finance.BalanceBlock;

import org.mapstruct.Mapper;
import pl.oskarinio.moneyisland.finance.BalanceBlock.AssetRepo.User;
import pl.oskarinio.moneyisland.finance.UserEntity;

@Mapper(componentModel = "spring")
public interface MapStruct {
    public Asset toAsset(AssetEntity assetEntity);
    public AssetEntity toAssetEntity(Asset asset);
    public User toUser(UserEntity userEntity);
    public UserEntity toUserEntity(User user);
}
