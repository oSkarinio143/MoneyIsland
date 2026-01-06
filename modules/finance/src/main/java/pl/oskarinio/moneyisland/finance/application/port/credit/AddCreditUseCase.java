package pl.oskarinio.moneyisland.finance.application.port.credit;

import pl.oskarinio.moneyisland.finance.domain.dto.form.CreditAddForm;

public interface AddCreditUseCase {
    void addCredit(CreditAddForm creditAddForm);
}
