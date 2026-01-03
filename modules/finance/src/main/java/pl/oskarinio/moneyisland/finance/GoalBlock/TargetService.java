package pl.oskarinio.moneyisland.finance.GoalBlock;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.GoalBlock.repository.TargetsRepository;
import pl.oskarinio.moneyisland.finance.UserRepository;

import java.math.BigDecimal;

@Service
public class TargetService implements SaveTargetValuesUseCase, LoadIncomeTargetUseCase, LoadExpenseTargetUseCase{
    private final TargetDomainService targetDomainService;

    public TargetService(TargetsRepository targetsRepository, UserRepository userRepository) {
        this.targetDomainService = new TargetDomainService(targetsRepository, userRepository);
    }

    @Override
    public void saveTargetValues(TargetRequest targetRequest) {
        targetDomainService.saveTargetValues(targetRequest);
    }

    public BigDecimal loadIncomeTarget(String username){
        return targetDomainService.loadIncomeTarget(username);
    }

    public BigDecimal loadExpenseTarget(String username){
        return targetDomainService.loadExpenseTarget(username);
    }
}
