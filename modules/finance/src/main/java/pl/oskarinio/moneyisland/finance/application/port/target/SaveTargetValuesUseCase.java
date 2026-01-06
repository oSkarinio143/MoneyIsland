package pl.oskarinio.moneyisland.finance.application.port.target;

import pl.oskarinio.moneyisland.finance.domain.dto.form.TargetForm;

public interface SaveTargetValuesUseCase {
    void saveTargetValues(TargetForm targetForm);
}
