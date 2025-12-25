package pl.oskarinio.moneyisland.finance.BalanceBlock.UserBalance;

import pl.oskarinio.moneyisland.finance.BalanceBlock.Asset;
import pl.oskarinio.moneyisland.finance.BalanceBlock.AssetRepo.AssetRepository;
import pl.oskarinio.moneyisland.finance.BalanceBlock.Dto.AssetFormRequest;
import pl.oskarinio.moneyisland.finance.UserRepository;

import java.math.BigDecimal;
import java.util.List;

public class UserBalancePanelDomainService {
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public UserBalancePanelDomainService(AssetRepository assetRepository, UserRepository userRepository) {
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    public void saveUserBalanceData(AssetFormRequest assetFormRequest){
        Asset asset = new Asset(assetFormRequest.getAssetName(), assetFormRequest.getAssetValue(), assetFormRequest.getUsername());
        assetRepository.saveAsset(asset);
    }

    public List<Asset> loadUserBalancePanels(String username){
        List<Asset> assetsList = assetRepository.findByUsername(username);
        return assetsList;
    }

    public void modifyUserBalancePanel(Long assetId, BigDecimal assetValue){
        Asset asset = assetRepository.getAsset(assetId);
        asset.setAssetValue(assetValue);
        assetRepository.saveAsset(asset);
    }

    public void deleteUserBalancePanel(Long assetId){
        assetRepository.deleteAsset(assetId);
    }
}
