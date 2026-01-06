package pl.oskarinio.moneyisland.finance.application.port.credit;

import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditPayRepaymentForm;

public interface PayRepaymentUseCase {
    void payRepayment(CreditPayRepaymentForm creditPayRepaymentForm);
}
