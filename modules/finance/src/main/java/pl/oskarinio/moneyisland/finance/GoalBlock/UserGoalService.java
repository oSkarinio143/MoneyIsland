package pl.oskarinio.moneyisland.finance.GoalBlock;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.GoalBlock.repository.UserTargetsRepository;

import java.math.BigDecimal;

@Service
public class UserGoalService implements SaveTargetValuesUseCase, LoadIncomeTargetUseCase, LoadExpenseTargetUseCase{
    private final UserGoalDomainService userGoalDomainService;

    public UserGoalService(UserTargetsRepository userTargetsRepository) {
        this.userGoalDomainService = new UserGoalDomainService(userTargetsRepository);
    }

    @Override
    public void saveTargetValues(GoalFormRequest goalFormRequest) {
        userGoalDomainService.saveTargetValues(goalFormRequest);
    }

    public BigDecimal loadIncomeTarget(String username){
        return userGoalDomainService.loadIncomeTarget(username);
    }

    public BigDecimal loadExpenseTarget(String username){
        return userGoalDomainService.loadExpenseTarget(username);
    }
}
