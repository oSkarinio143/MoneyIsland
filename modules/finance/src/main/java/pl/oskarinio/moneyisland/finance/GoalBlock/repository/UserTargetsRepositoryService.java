package pl.oskarinio.moneyisland.finance.GoalBlock.repository;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.BalanceBlock.MapStruct;
import pl.oskarinio.moneyisland.finance.GoalBlock.UserTargets;

@Service
public class UserTargetsRepositoryService implements UserTargetsRepository {
    private final UserTargetsRepositoryJpa userTargetsRepositoryJpa;
    private final MapStruct mapper;

    public UserTargetsRepositoryService(UserTargetsRepositoryJpa userTargetsRepositoryJpa, MapStruct mapper) {
        this.userTargetsRepositoryJpa = userTargetsRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public void saveUserTargets(UserTargets userTargets) {
        userTargetsRepositoryJpa.save(mapper.toUserTargetsEntity(userTargets));
    }

    @Override
    public UserTargets loadUserTargets(String username) {
        System.out.println(username);
        return mapper.toUserTargets(userTargetsRepositoryJpa.findByUsername(username));
    }
}
