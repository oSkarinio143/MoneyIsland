package pl.oskarinio.moneyisland.finance.domain.port;

import pl.oskarinio.moneyisland.finance.domain.dto.Credit;

import java.util.List;

public interface CreditRepository {
    void save(Credit credit);
    List<Credit> findCreditsByUsername(String username);
    void delete(Credit credit);
}
