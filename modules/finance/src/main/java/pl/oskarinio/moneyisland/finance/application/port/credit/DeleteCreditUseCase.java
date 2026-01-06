package pl.oskarinio.moneyisland.finance.application.port.credit;

import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditDeleteForm;

public interface DeleteCreditUseCase {
    void deleteCredit(CreditDeleteForm creditDeleteForm);
}
