package pl.oskarinio.moneyisland.finance.map;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.oskarinio.moneyisland.finance.BalanceBlock.Asset;
import pl.oskarinio.moneyisland.finance.BalanceBlock.AssetEntity;
import pl.oskarinio.moneyisland.finance.GoalBlock.Target;
import pl.oskarinio.moneyisland.finance.GoalBlock.repository.TargetEntity;
import pl.oskarinio.moneyisland.finance.User;
import pl.oskarinio.moneyisland.finance.UserEntity;
import pl.oskarinio.moneyisland.finance.creditBlock.Credit;
import pl.oskarinio.moneyisland.finance.creditBlock.CreditEntity;
import pl.oskarinio.moneyisland.finance.historyBlock.Dto.HistoryExpense;
import pl.oskarinio.moneyisland.finance.historyBlock.Dto.HistoryIncome;
import pl.oskarinio.moneyisland.finance.historyBlock.entity.HistoryExpenseEntity;
import pl.oskarinio.moneyisland.finance.historyBlock.entity.HistoryIncomeEntity;

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
