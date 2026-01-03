package pl.oskarinio.moneyisland.finance.GoalBlock;

import pl.oskarinio.moneyisland.finance.GoalBlock.repository.TargetsRepository;
import pl.oskarinio.moneyisland.finance.UserRepository;

import java.math.BigDecimal;

public class TargetDomainService {
    private final TargetsRepository targetsRepository;
    private final UserRepository userRepository;

    public TargetDomainService(TargetsRepository targetsRepository, UserRepository userRepository) {
        this.targetsRepository = targetsRepository;
        this.userRepository = userRepository;
    }

    public void saveTargetValues(TargetRequest targetRequest){
        Target target = getUserTargets(targetRequest.getUsername());
        target.setUserId(userRepository.findByUsername(targetRequest.getUsername()).getId());
        if(targetRequest.getTargetIncome() != null)
            target.setIncomeTarget(targetRequest.getTargetIncome());
        if(targetRequest.getTargetExpense() != null)
            target.setExpenseTarget(targetRequest.getTargetExpense());
        targetsRepository.saveUserTargets(target);
    }

    public Target getUserTargets(String username){
        if(targetsRepository.loadUserTarget(username) == null)
            return new Target(username);
        return targetsRepository.loadUserTarget(username);
    }

    public BigDecimal loadIncomeTarget(String username){
        if(targetsRepository.loadUserTarget(username) == null)
            return BigDecimal.ZERO;
        return targetsRepository.loadUserTarget(username).getIncomeTarget();
    }

    public BigDecimal loadExpenseTarget(String username){
        if(targetsRepository.loadUserTarget(username) == null)
            return BigDecimal.ZERO;
        return targetsRepository.loadUserTarget(username).getExpenseTarget();
    }
}
