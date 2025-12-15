package pl.oskarinio.moneyisland.auth.domain.port;


import pl.oskarinio.moneyisland.auth.domain.dto.UserServiceData;

public interface Token {
    String generateToken(UserServiceData loginServiceData, long seconds);
    String extractUsername(String token);
    boolean isTokenExpiredSafe(String token);
}
