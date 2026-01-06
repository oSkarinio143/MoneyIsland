package pl.oskarinio.moneyisland.finance.domain.service;

import pl.oskarinio.moneyisland.finance.domain.dto.Credit;
import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditAddForm;
import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditDeleteForm;
import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditPayRepaymentForm;
import pl.oskarinio.moneyisland.finance.domain.port.UserRepository;
import pl.oskarinio.moneyisland.finance.domain.port.CreditRepository;

import java.math.BigDecimal;
import java.util.List;

public class CreditDomainService {
    private final CreditRepository creditRepository;
    private final UserRepository userRepository;

    public CreditDomainService(CreditRepository creditRepository, UserRepository userRepository) {
        this.creditRepository = creditRepository;
        this.userRepository = userRepository;
    }

    public List<Credit> loadCredits(String username){
        return creditRepository.findCreditsByUsername(username);
    }

    public void addCredit(CreditAddForm creditAddForm){
        Credit credit = new Credit(creditAddForm.getUsername(),
                creditAddForm.getCreditName(),
                creditAddForm.getPercent(),
                creditAddForm.getMonths(),
                creditAddForm.getValue(),
                creditAddForm.getFinalValue(),
                BigDecimal.ZERO,
                creditAddForm.getFinalValue(),
                creditAddForm.getRepayment(),
                BigDecimal.ZERO);
        credit.setUser(userRepository.findByUsername(creditAddForm.getUsername()));
        creditRepository.save(credit);
    }

    public void payRepayment(CreditPayRepaymentForm creditPayRepaymentForm){
        List<Credit> credits = loadCredits(creditPayRepaymentForm.getUsername());
        Credit moddifiedCredit = credits.stream()
                .filter(v -> v.getCreditName().equals(creditPayRepaymentForm.getCreditName()))
                .toList()
                .getFirst();

        creditRepository.save(getModdifiedCredit(creditPayRepaymentForm,moddifiedCredit));
    }

    public void deleteCredit(CreditDeleteForm creditDeleteForm){
        List<Credit> credits = creditRepository.findCreditsByUsername(creditDeleteForm.getUsername());
        Credit credit = credits.stream()
                .filter(v -> v.getCreditName().equals(creditDeleteForm.getCreditName()))
                .toList()
                .getFirst();
        creditRepository.delete(credit);
    }

    private Credit getModdifiedCredit(CreditPayRepaymentForm creditPayRepaymentForm, Credit creditToModify){
        BigDecimal payedValue = creditToModify.getRepayment().multiply(creditPayRepaymentForm.getQuantity());
        BigDecimal notPayedValue = creditToModify.getFinalValue().subtract(payedValue);
        BigDecimal repaymentQuantity = creditPayRepaymentForm.getQuantity();

        creditToModify.setPayedValue(payedValue);
        creditToModify.setNotPayedValue(notPayedValue);
        creditToModify.setRepaymentQuantity(repaymentQuantity);
        return creditToModify;
    }
}
