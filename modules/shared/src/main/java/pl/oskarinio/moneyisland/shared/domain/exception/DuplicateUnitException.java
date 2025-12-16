package pl.oskarinio.moneyisland.shared.domain.exception;

public class DuplicateUnitException extends RuntimeException {
    public DuplicateUnitException(String message) {
        super(message);
    }
}
