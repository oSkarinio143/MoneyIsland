package pl.oskarinio.moneyisland.finance.GoalBlock.repository;

import pl.oskarinio.moneyisland.finance.GoalBlock.Target;

public interface TargetsRepository {
    void saveUserTargets(Target target);
    Target loadUserTarget(String username);
}
