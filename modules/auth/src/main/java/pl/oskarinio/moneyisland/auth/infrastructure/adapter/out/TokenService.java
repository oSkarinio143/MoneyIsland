package pl.oskarinio.moneyisland.auth.infrastructure.adapter.out;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.oskarinio.moneyisland.auth.domain.dto.UserServiceData;
import pl.oskarinio.moneyisland.auth.domain.port.Token;

import javax.crypto.SecretKey;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class TokenService implements Token {
//    @Value("${jwt.secret.base64}")
//    private String secretKeyString;
    private final SecretKey secretKey;
    private final Clock clock;

    public TokenService(Clock clock, @Value("${jwt.secret.base64}") String secretKeyString) {
        this.clock = clock;
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyString));
    }

    @Override
    public String generateToken(UserServiceData loginServiceData, long seconds) {
        log.debug("Generuje token dla uzytkownika. Nazwa = {}", loginServiceData.getUsername());
        Instant now = Instant.now(clock);
        Date issuedAt = Date.from(now);
        Date expiration = Date.from(now.plus(seconds, ChronoUnit.SECONDS));
        return Jwts.builder()
                .setSubject(loginServiceData.getUsername())
                .claim("roles", loginServiceData.getRoles())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        String username = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        log.trace("Wyciagam nazwe uzytkownika z tokenu. Nazwa = {}", username);
        return username;
    }

    @Override
    public boolean isTokenExpiredSafe(String token) {
        boolean isTokenExpired = true;
        try{
            isTokenExpired = isTokenExpired(token);
        }catch (ExpiredJwtException | IllegalArgumentException ignored){}
        return isTokenExpired;
    }

    private boolean isTokenExpired(String token) {
        Instant now = Instant.now(clock);
        Date expirationDate = Date.from(now);
        Date expiration = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        boolean expired = expiration.before(expirationDate);
        log.trace("Sprawdzam czy token wygasl. Wynik = {}", expired);
        return expired;
    }
}
