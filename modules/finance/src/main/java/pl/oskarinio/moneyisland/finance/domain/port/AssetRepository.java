package pl.oskarinio.moneyisland.finance.domain.port;

import pl.oskarinio.moneyisland.finance.domain.dto.Asset;

import java.util.List;

public interface AssetRepository {
    void saveAsset(Asset asset);
    List<Asset> findByUsername(String username);
    void deleteAsset(Long assetId);
    Asset getAsset(Long assetId);
}
