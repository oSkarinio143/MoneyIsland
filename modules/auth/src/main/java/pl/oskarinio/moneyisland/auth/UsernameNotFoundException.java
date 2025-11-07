package pl.oskarinio.moneyisland.auth;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException() {}

    public UsernameNotFoundException(String message) {
        super(message);
    }

}
