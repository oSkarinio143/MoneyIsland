package pl.oskarinio.moneyisland.finance.creditBlock;

import java.util.List;

public interface LoadCreditsUseCase {
    List<Credit> loadCredits(String username);
}
