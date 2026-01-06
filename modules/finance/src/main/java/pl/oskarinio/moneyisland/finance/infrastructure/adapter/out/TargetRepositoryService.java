package pl.oskarinio.moneyisland.finance.infrastructure.adapter.out;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.domain.dto.Target;
import pl.oskarinio.moneyisland.finance.infrastructure.db.entity.TargetEntity;
import pl.oskarinio.moneyisland.finance.domain.port.TargetRepository;
import pl.oskarinio.moneyisland.finance.infrastructure.service.TargetRepositoryJpa;
import pl.oskarinio.moneyisland.finance.domain.port.UserRepository;
import pl.oskarinio.moneyisland.finance.infrastructure.db.MapStruct;

@Service
public class TargetRepositoryService implements TargetRepository {
    private final TargetRepositoryJpa targetRepositoryJpa;
    private final UserRepository userRepository;
    private final MapStruct mapper;

    public TargetRepositoryService(TargetRepositoryJpa targetRepositoryJpa, UserRepository userRepository, MapStruct mapper) {
        this.targetRepositoryJpa = targetRepositoryJpa;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveUserTargets(Target target) {
        TargetEntity targetEntity = mapper.toTargetEntity(target);
        targetEntity.setUser(mapper.toUserEntity(userRepository.findByUserId(target.getUserId())));
        targetRepositoryJpa.save(targetEntity);
    }

    @Override
    public Target loadUserTarget(String username) {
        return mapper.toTarget(targetRepositoryJpa.findByUsername(username));
    }
}
