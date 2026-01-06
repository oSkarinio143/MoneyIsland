package pl.oskarinio.moneyisland.finance.application.port.credit;

import pl.oskarinio.moneyisland.finance.domain.dto.Credit;

import java.util.List;

public interface LoadCreditsUseCase {
    List<Credit> loadCredits(String username);
}
