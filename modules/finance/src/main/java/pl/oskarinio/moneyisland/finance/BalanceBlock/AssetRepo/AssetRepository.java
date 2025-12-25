package pl.oskarinio.moneyisland.finance.BalanceBlock.AssetRepo;

import pl.oskarinio.moneyisland.finance.BalanceBlock.Asset;

import java.util.List;

public interface AssetRepository {
    void saveAsset(Asset asset);
    List<Asset> findByUsername(String username);
    void deleteAsset(Long assetId);
    Asset getAsset(Long assetId);
}
