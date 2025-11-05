package pl.oskarinio.moneyisland.auth.domain.port.out;


import pl.oskarinio.moneyisland.auth.domain.model.user.UserServiceData;

public interface Token {
    String generateToken(UserServiceData loginServiceData, long seconds);
    String extractUsername(String token);
    boolean isTokenExpiredSafe(String token);
}
