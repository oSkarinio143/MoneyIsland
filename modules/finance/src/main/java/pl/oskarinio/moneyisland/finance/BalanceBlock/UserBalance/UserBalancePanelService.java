package pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalance;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.BalanceBlock.Asset;
import pl.oskarinio.moneyisland.finance.BalanceBlock.AssetRepo.AssetRepository;
import pl.oskarinio.moneyisland.finance.BalanceBlock.Dto.AssetFormRequest;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.DeleteUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.LoadUserBalancePanelsUseCase;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.ModifyUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalanceUseCases.SaveUserBalancePanelUseCase;
import pl.oskarinio.moneyisland.finance.UserRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserBalancePanelService implements SaveUserBalancePanelUseCase, LoadUserBalancePanelsUseCase, ModifyUserBalancePanelUseCase, DeleteUserBalancePanelUseCase {
    private final UserBalancePanelDomainService userBalancePanelDomainService;

    public UserBalancePanelService(AssetRepository assetRepository,
                                   UserRepository userRepository) {
        this.userBalancePanelDomainService = new UserBalancePanelDomainService(assetRepository, userRepository);
    }


    @Override
    public void saveUserBalancePanel(AssetFormRequest assetFormRequest) {
        userBalancePanelDomainService.saveUserBalanceData(assetFormRequest);
    }

    @Override
    public List<Asset> loadUserBalancePanels(String username) {
        return userBalancePanelDomainService.loadUserBalancePanels(username);
    }

    @Override
    public void deleteUserBalancePanel(Long assetId) {
        userBalancePanelDomainService.deleteUserBalancePanel(assetId);
    }

    @Override
    public void modifyUserBalancePanel(Long assetId, BigDecimal assetValue) {
        userBalancePanelDomainService.modifyUserBalancePanel(assetId, assetValue);
    }
}
