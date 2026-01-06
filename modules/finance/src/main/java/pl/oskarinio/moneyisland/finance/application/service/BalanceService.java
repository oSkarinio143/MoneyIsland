package pl.oskarinio.moneyisland.finance.application.service;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.domain.service.BalanceDomainService;
import pl.oskarinio.moneyisland.finance.domain.dto.Asset;
import pl.oskarinio.moneyisland.finance.domain.port.AssetRepository;
import pl.oskarinio.moneyisland.finance.domain.dto.form.AssetAddForm;
import pl.oskarinio.moneyisland.finance.application.port.balance.DeleteUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.application.port.balance.LoadUserBalancePanelsUseCase;
import pl.oskarinio.moneyisland.finance.application.port.balance.ModifyUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.application.port.balance.SaveUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.domain.port.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BalanceService implements SaveUserBalancePanelUseCase, LoadUserBalancePanelsUseCase, ModifyUserBalancePanelUseCase, DeleteUserBalancePanelUseCase {
    private final BalanceDomainService balanceDomainService;

    public BalanceService(AssetRepository assetRepository,
                          UserRepository userRepository) {
        this.balanceDomainService = new BalanceDomainService(assetRepository, userRepository);
    }


    @Override
    public void saveUserBalancePanel(AssetAddForm assetAddForm) {
        balanceDomainService.saveUserBalanceData(assetAddForm);
    }

    @Override
    public List<Asset> loadUserBalancePanels(String username) {
        return balanceDomainService.loadUserBalancePanels(username);
    }

    @Override
    public void deleteUserBalancePanel(Long assetId) {
        balanceDomainService.deleteUserBalancePanel(assetId);
    }

    @Override
    public void modifyUserBalancePanel(Long assetId, BigDecimal assetValue) {
        balanceDomainService.modifyUserBalancePanel(assetId, assetValue);
    }
}
