package pl.oskarinio.moneyisland.shared.domain.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException() {}

    public UsernameNotFoundException(String message) {
        super(message);
    }

}
