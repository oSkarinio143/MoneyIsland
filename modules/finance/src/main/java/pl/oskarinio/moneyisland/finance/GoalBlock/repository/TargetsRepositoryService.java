package pl.oskarinio.moneyisland.finance.GoalBlock.repository;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.GoalBlock.Target;
import pl.oskarinio.moneyisland.finance.UserRepository;
import pl.oskarinio.moneyisland.finance.map.MapStruct;

@Service
public class TargetsRepositoryService implements TargetsRepository {
    private final TargetsRepositoryJpa targetsRepositoryJpa;
    private final UserRepository userRepository;
    private final MapStruct mapper;

    public TargetsRepositoryService(TargetsRepositoryJpa targetsRepositoryJpa, UserRepository userRepository, MapStruct mapper) {
        this.targetsRepositoryJpa = targetsRepositoryJpa;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveUserTargets(Target target) {
        TargetEntity targetEntity = mapper.toTargetEntity(target);
        targetEntity.setUser(mapper.toUserEntity(userRepository.findByUserId(target.getUserId())));
        targetsRepositoryJpa.save(targetEntity);
    }

    @Override
    public Target loadUserTarget(String username) {
        return mapper.toTarget(targetsRepositoryJpa.findByUsername(username));
    }
}
