package pl.oskarinio.moneyisland.finance.application.service;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.application.port.target.LoadExpenseTargetUseCase;
import pl.oskarinio.moneyisland.finance.application.port.target.LoadIncomeTargetUseCase;
import pl.oskarinio.moneyisland.finance.application.port.target.SaveTargetValuesUseCase;
import pl.oskarinio.moneyisland.finance.domain.service.TargetDomainService;
import pl.oskarinio.moneyisland.finance.domain.dto.form.TargetForm;
import pl.oskarinio.moneyisland.finance.domain.port.TargetRepository;
import pl.oskarinio.moneyisland.finance.domain.port.UserRepository;

import java.math.BigDecimal;

@Service
public class TargetService implements SaveTargetValuesUseCase, LoadIncomeTargetUseCase, LoadExpenseTargetUseCase {
    private final TargetDomainService targetDomainService;

    public TargetService(TargetRepository targetRepository, UserRepository userRepository) {
        this.targetDomainService = new TargetDomainService(targetRepository, userRepository);
    }

    @Override
    public void saveTargetValues(TargetForm targetForm) {
        targetDomainService.saveTargetValues(targetForm);
    }

    public BigDecimal loadIncomeTarget(String username){
        return targetDomainService.loadIncomeTarget(username);
    }

    public BigDecimal loadExpenseTarget(String username){
        return targetDomainService.loadExpenseTarget(username);
    }
}
