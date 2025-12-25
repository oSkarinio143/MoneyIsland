package pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases;

import pl.oskarinio.moneyisland.finance.BalanceBlock.Asset;

import java.util.List;

public interface LoadUserBalancePanelsUseCase {
    public List<Asset> loadUserBalancePanels(String username);
}
