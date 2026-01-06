package pl.oskarinio.moneyisland.finance.application.port.balance;

import pl.oskarinio.moneyisland.finance.domain.dto.form.AssetAddForm;

public interface SaveUserBalancePanelUseCase {
    public void saveUserBalancePanel(AssetAddForm assetAddForm);
}
