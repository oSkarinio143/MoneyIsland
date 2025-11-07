package pl.oskarinio.moneyisland.shared.uncategorized;



public interface Token {
    String generateToken(UserServiceData loginServiceData, long seconds);
    String extractUsername(String token);
    boolean isTokenExpiredSafe(String token);
}
