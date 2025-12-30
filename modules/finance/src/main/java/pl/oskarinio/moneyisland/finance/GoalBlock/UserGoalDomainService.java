package pl.oskarinio.moneyisland.finance.GoalBlock;

import pl.oskarinio.moneyisland.finance.GoalBlock.repository.UserTargetsRepository;

import java.math.BigDecimal;

public class UserGoalDomainService {
    private final UserTargetsRepository userTargetsRepository;

    public UserGoalDomainService(UserTargetsRepository userTargetsRepository) {
        this.userTargetsRepository = userTargetsRepository;
    }

    public void saveTargetValues(GoalFormRequest goalFormRequest){
        UserTargets userTargets = getUserTargets(goalFormRequest.getUsername());
        if(goalFormRequest.getTargetIncome() != null)
            userTargets.setIncomeTarget(goalFormRequest.getTargetIncome());
        if(goalFormRequest.getTargetExpense() != null)
            userTargets.setExpenseTarget(goalFormRequest.getTargetExpense());
        userTargetsRepository.saveUserTargets(userTargets);
    }

    public UserTargets getUserTargets(String username){
        if(userTargetsRepository.loadUserTargets(username) == null)
            return new UserTargets(username);
        return userTargetsRepository.loadUserTargets(username);
    }

    public BigDecimal loadIncomeTarget(String username){
        if(userTargetsRepository.loadUserTargets(username) == null)
            return BigDecimal.ZERO;
        return userTargetsRepository.loadUserTargets(username).getIncomeTarget();
    }

    public BigDecimal loadExpenseTarget(String username){
        if(userTargetsRepository.loadUserTargets(username) == null)
            return BigDecimal.ZERO;
        return userTargetsRepository.loadUserTargets(username).getExpenseTarget();
    }
}
