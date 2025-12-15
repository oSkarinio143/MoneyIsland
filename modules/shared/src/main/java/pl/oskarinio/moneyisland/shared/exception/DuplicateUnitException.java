package pl.oskarinio.moneyisland.shared.exception;

public class DuplicateUnitException extends RuntimeException {
    public DuplicateUnitException(String message) {
        super(message);
    }
}
