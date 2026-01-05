package pl.oskarinio.moneyisland.finance.creditBlock;

import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.finance.UserRepository;
import pl.oskarinio.moneyisland.finance.creditBlock.repo.CreditRepository;

import java.util.List;

@Service
public class CreditService implements LoadCreditsUseCase, AddCreditUseCase, PayRepaymentUseCase, DeleteCreditUseCase{
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
