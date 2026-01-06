package pl.oskarinio.moneyisland.finance.infrastructure.adapter.out;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.domain.dto.Asset;
import pl.oskarinio.moneyisland.finance.infrastructure.service.AssetRepositoryJpa;
import pl.oskarinio.moneyisland.finance.domain.port.AssetRepository;
import pl.oskarinio.moneyisland.finance.infrastructure.db.MapStruct;

import java.util.List;

@Service
public class AssetRepositoryService implements AssetRepository {
    private final AssetRepositoryJpa assetRepositoryJpa;
    private final MapStruct mapper;

    public AssetRepositoryService(AssetRepositoryJpa assetRepositoryJpa, MapStruct mapper) {
        this.assetRepositoryJpa = assetRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public void saveAsset(Asset asset) {
        System.out.println(asset);
        System.out.println("po mapping - " + mapper.toAssetEntity(asset));
        assetRepositoryJpa.save(mapper.toAssetEntity(asset));
    }

    @Override
    public List<Asset> findByUsername(String username) {
        return assetRepositoryJpa.findByUsername(username).stream()
                .map(mapper::toAsset)
                .toList();
    }

    @Override
    public void deleteAsset(Long assetId) {
        assetRepositoryJpa.deleteById(assetId);
    }

    @Override
    public Asset getAsset(Long assetId) {
        return mapper.toAsset(assetRepositoryJpa.getReferenceById(assetId));
    }
}
