package pl.oskarinio.moneyisland.auth.infrastructure.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException() {}

    public UsernameNotFoundException(String message) {
        super(message);
    }

}
