package pl.oskarinio.moneyisland.finance.BalanceBlock;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import pl.oskarinio.moneyisland.finance.BalanceBlock.AssetRepo.User;
import pl.oskarinio.moneyisland.finance.GoalBlock.UserTargets;
import pl.oskarinio.moneyisland.finance.GoalBlock.repository.UserTargetsEntity;
import pl.oskarinio.moneyisland.finance.UserEntity;
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
    public User toUser(UserEntity userEntity);
    public UserEntity toUserEntity(User user);
    public HistoryExpense toHistoryExpense(HistoryExpenseEntity historyExpenseEntity);
    public HistoryExpenseEntity toHistoryExpenseEntity(HistoryExpense historyExpense);
    public HistoryIncome toHistoryIncome(HistoryIncomeEntity historyIncomeEntity);
    public HistoryIncomeEntity toHistoryIncomeEntity(HistoryIncome historyIncome);
    public UserTargets toUserTargets(UserTargetsEntity userTargetsEntity);
    public UserTargetsEntity toUserTargetsEntity(UserTargets userTargets);
}
