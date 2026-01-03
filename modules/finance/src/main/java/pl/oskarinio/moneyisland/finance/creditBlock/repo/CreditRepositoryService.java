package pl.oskarinio.moneyisland.finance.creditBlock.repo;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.map.MapStruct;
import pl.oskarinio.moneyisland.finance.creditBlock.Credit;

import java.util.List;

@Service
public class CreditRepositoryService implements CreditRepository{
    private final CreditRepositoryJpa creditRepositoryJpa;
    private final MapStruct mapper;

    public CreditRepositoryService(CreditRepositoryJpa creditRepositoryJpa, MapStruct mapper) {
        this.creditRepositoryJpa = creditRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public void save(Credit credit) {
        creditRepositoryJpa.save(mapper.toCreditEntity(credit));
    }

    @Override
    public List<Credit> findCreditsByUsername(String username) {
        return creditRepositoryJpa.findCreditsByUsername(username).stream()
                .map(mapper::toCredit)
                .toList();
    }

    @Override
    public void delete(Credit credit) {
        creditRepositoryJpa.delete(mapper.toCreditEntity(credit));
    }
}
