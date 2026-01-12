package pl.oskarinio.moneyisland.finance.domain.service;

import pl.oskarinio.moneyisland.finance.domain.dto.Asset;
import pl.oskarinio.moneyisland.finance.domain.port.AssetRepository;
import pl.oskarinio.moneyisland.finance.domain.dto.form.AssetAddForm;
import pl.oskarinio.moneyisland.finance.domain.port.UserRepository;

import java.math.BigDecimal;
import java.util.List;

public class BalanceDomainService {
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public BalanceDomainService(AssetRepository assetRepository, UserRepository userRepository) {
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    public void saveUserBalanceData(AssetAddForm assetAddForm){
        Asset asset = new Asset(assetAddForm.getAssetName(), assetAddForm.getAssetValue(), assetAddForm.getUsername());
        asset.setUser(userRepository.findByUsername(asset.getUsername()));
        assetRepository.saveAsset(asset);
    }

    public List<Asset> loadUserBalancePanels(String username){
        return assetRepository.findByUsername(username);
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
