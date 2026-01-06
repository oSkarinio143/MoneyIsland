package pl.oskarinio.moneyisland.finance.infrastructure.db;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.oskarinio.moneyisland.finance.domain.dto.Asset;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.AssetEntity;
import pl.oskarinio.moneyisland.finance.domain.dto.Target;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.TargetEntity;
import pl.oskarinio.moneyisland.finance.domain.dto.User;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.UserEntity;
import pl.oskarinio.moneyisland.finance.domain.dto.Credit;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.CreditEntity;
import pl.oskarinio.moneyisland.finance.domain.dto.HistoryExpense;
import pl.oskarinio.moneyisland.finance.domain.dto.HistoryIncome;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.HistoryExpenseEntity;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.HistoryIncomeEntity;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED
)
public interface MapStruct {
    public Asset toAsset(AssetEntity assetEntity);
    public AssetEntity toAssetEntity(Asset asset);
    public UserEntity toUserEntity(User user);
    public User toUser(UserEntity userEntity);
    public HistoryExpense toHistoryExpense(HistoryExpenseEntity historyExpenseEntity);
    public HistoryExpenseEntity toHistoryExpenseEntity(HistoryExpense historyExpense);
    public HistoryIncome toHistoryIncome(HistoryIncomeEntity historyIncomeEntity);
    public HistoryIncomeEntity toHistoryIncomeEntity(HistoryIncome historyIncome);
    @Mapping(source = "user.id", target = "userId")
    public Target toTarget(TargetEntity targetEntity);
//    @Mapping(source = "userId", target = "user")
    public TargetEntity toTargetEntity(Target target);
    public Credit toCredit(CreditEntity creditEntity);
    public CreditEntity toCreditEntity(Credit credit);
//    default UserEntity mapIdToUser(Long id) {
//        if (id == null)
//            return null;
//        UserEntity userEntity = new UserEntity();
//        return
//    }
}
