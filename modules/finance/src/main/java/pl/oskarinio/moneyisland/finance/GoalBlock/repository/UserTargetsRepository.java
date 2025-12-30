package pl.oskarinio.moneyisland.finance.GoalBlock.repository;

import pl.oskarinio.moneyisland.finance.GoalBlock.UserTargets;

public interface UserTargetsRepository {
    void saveUserTargets(UserTargets userTargets);
    UserTargets loadUserTargets(String username);
}
