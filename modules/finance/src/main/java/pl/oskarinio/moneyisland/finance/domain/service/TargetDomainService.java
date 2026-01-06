package pl.oskarinio.moneyisland.finance.domain.service;

import pl.oskarinio.moneyisland.finance.domain.dto.Target;
import pl.oskarinio.moneyisland.finance.domain.dto.form.TargetForm;
import pl.oskarinio.moneyisland.finance.domain.port.TargetRepository;
import pl.oskarinio.moneyisland.finance.domain.port.UserRepository;

import java.math.BigDecimal;

public class TargetDomainService {
    private final TargetRepository targetRepository;
    private final UserRepository userRepository;

    public TargetDomainService(TargetRepository targetRepository, UserRepository userRepository) {
        this.targetRepository = targetRepository;
        this.userRepository = userRepository;
    }

    public void saveTargetValues(TargetForm targetForm){
        Target target = getUserTargets(targetForm.getUsername());
        target.setUserId(userRepository.findByUsername(targetForm.getUsername()).getId());
        if(targetForm.getTargetIncome() != null)
            target.setIncomeTarget(targetForm.getTargetIncome());
        if(targetForm.getTargetExpense() != null)
            target.setExpenseTarget(targetForm.getTargetExpense());
        targetRepository.saveUserTargets(target);
    }

    public Target getUserTargets(String username){
        if(targetRepository.loadUserTarget(username) == null)
            return new Target(username);
        return targetRepository.loadUserTarget(username);
    }

    public BigDecimal loadIncomeTarget(String username){
        if(targetRepository.loadUserTarget(username) == null)
            return BigDecimal.ZERO;
        return targetRepository.loadUserTarget(username).getIncomeTarget();
    }

    public BigDecimal loadExpenseTarget(String username){
        if(targetRepository.loadUserTarget(username) == null)
            return BigDecimal.ZERO;
        return targetRepository.loadUserTarget(username).getExpenseTarget();
    }
}
