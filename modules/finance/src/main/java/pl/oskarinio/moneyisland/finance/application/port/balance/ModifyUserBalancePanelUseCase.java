package pl.oskarinio.moneyisland.finance.application.port.balance;

import java.math.BigDecimal;

public interface ModifyUserBalancePanelUseCase {
    void modifyUserBalancePanel(Long assetId, BigDecimal assetValue);
}
