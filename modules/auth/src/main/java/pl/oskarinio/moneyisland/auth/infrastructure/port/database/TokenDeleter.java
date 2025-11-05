package pl.oskarinio.moneyisland.auth.infrastructure.port.database;

public interface TokenDeleter {
    void cleanExpiredTokens();
}
