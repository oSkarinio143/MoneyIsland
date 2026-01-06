package pl.oskarinio.moneyisland.finance.application.port.balance;

import pl.oskarinio.moneyisland.finance.domain.dto.Asset;

import java.util.List;

public interface LoadUserBalancePanelsUseCase {
    public List<Asset> loadUserBalancePanels(String username);
}
