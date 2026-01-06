package pl.oskarinio.moneyisland.shared.domain.exception;

public class RegistrationDataException extends RuntimeException {
    public RegistrationDataException(String message) {
        super(message);
    }

    public RegistrationDataException() {}
}
