package pl.oskarinio.moneyisland.finance.creditBlock;

import pl.oskarinio.moneyisland.finance.creditBlock.repo.CreditRepository;

import java.math.BigDecimal;
import java.util.List;

public class CreditDomainService {
    private final CreditRepository creditRepository;

    public CreditDomainService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
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
        System.out.println(credits);
        Credit credit = credits.stream()
                .filter(v -> v.getCreditName().equals(creditDeleteForm.getCreditName()))
                .toList()
                .getFirst();
        creditRepository.delete(credit);
    }

    private Credit getModdifiedCredit(CreditPayRepaymentForm creditPayRepaymentForm, Credit creditToModify){
        BigDecimal payedValue = creditToModify.getRepayment().multiply(creditPayRepaymentForm.getQuantity());
        BigDecimal unPayedValue = creditToModify.getFinalValue().subtract(payedValue);
        BigDecimal repaymentQuantity = creditPayRepaymentForm.getQuantity();

        creditToModify.setPayedValue(payedValue);
        creditToModify.setUnpayedValue(unPayedValue);
        creditToModify.setRepaymentQuantity(repaymentQuantity);
        return creditToModify;
    }

}
