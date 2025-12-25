package pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases;

import java.math.BigDecimal;

public interface ModifyUserBalancePanelUseCase {
    void modifyUserBalancePanel(Long assetId, BigDecimal assetValue);
}
