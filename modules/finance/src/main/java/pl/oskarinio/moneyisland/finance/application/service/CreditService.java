package pl.oskarinio.moneyisland.finance.application.service;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.application.port.credit.AddCreditUseCase;
import pl.oskarinio.moneyisland.finance.application.port.credit.DeleteCreditUseCase;
import pl.oskarinio.moneyisland.finance.application.port.credit.LoadCreditsUseCase;
import pl.oskarinio.moneyisland.finance.application.port.credit.PayRepaymentUseCase;
import pl.oskarinio.moneyisland.finance.domain.dto.Credit;
import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditAddForm;
import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditDeleteForm;
import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditPayRepaymentForm;
import pl.oskarinio.moneyisland.finance.domain.service.CreditDomainService;
import pl.oskarinio.moneyisland.finance.domain.port.UserRepository;
import pl.oskarinio.moneyisland.finance.domain.port.CreditRepository;

import java.util.List;

@Service
public class CreditService implements LoadCreditsUseCase, AddCreditUseCase, PayRepaymentUseCase, DeleteCreditUseCase {
    private final CreditDomainService creditDomainService;

    public CreditService(CreditRepository creditRepository, UserRepository userRepository) {
        this.creditDomainService = new CreditDomainService(creditRepository, userRepository);
    }

    @Override
    public List<Credit> loadCredits(String username) {
        return creditDomainService.loadCredits(username);
    }

    @Override
    public void addCredit(CreditAddForm creditAddForm) {
        creditDomainService.addCredit(creditAddForm);
    }

    @Override
    public void payRepayment(CreditPayRepaymentForm creditPayRepaymentForm) {
        creditDomainService.payRepayment(creditPayRepaymentForm);
    }

    @Override
    public void deleteCredit(CreditDeleteForm creditDeleteForm) {
        creditDomainService.deleteCredit(creditDeleteForm);
    }
}
