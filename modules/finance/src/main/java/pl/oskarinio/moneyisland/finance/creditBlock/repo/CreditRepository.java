package pl.oskarinio.moneyisland.finance.creditBlock.repo;

import pl.oskarinio.moneyisland.finance.creditBlock.Credit;

import java.util.List;

public interface CreditRepository {
    void save(Credit credit);
    List<Credit> findCreditsByUsername(String username);
    void delete(Credit credit);
}
