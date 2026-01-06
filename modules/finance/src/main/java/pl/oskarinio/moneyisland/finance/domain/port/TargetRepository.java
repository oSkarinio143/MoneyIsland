package pl.oskarinio.moneyisland.finance.domain.port;

import pl.oskarinio.moneyisland.finance.domain.dto.Target;

public interface TargetRepository {
    void saveUserTargets(Target target);
    Target loadUserTarget(String username);
}
