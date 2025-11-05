package pl.oskarinio.moneyisland.auth.infrastructure.port.communication;

import org.springframework.validation.BindingResult;

public interface ExceptionMessageCreator {
    String createErrorMessage(BindingResult bindingResult);
}
