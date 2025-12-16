package pl.oskarinio.moneyisland.gateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import pl.oskarinio.moneyisland.gateway.web.Route;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.crypto.SecretKey;
import java.net.URI;
import java.util.Base64;
import java.util.List;

@Component
public class WebExpiredTokenFilter implements WebFilter {
    private final String COOKIE_ACCESS_TOKEN = "accessToken";
    private final SecretKey secretKey;
    private static final List<String> PUBLIC_PATHS = List.of(
            Route.MAIN + Route.REGISTER,
            Route.MAIN + Route.LOGIN);

    public WebExpiredTokenFilter(@Value("${jwt.secret.base64}") String secretKeyString) {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyString));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (PUBLIC_PATHS.contains(exchange.getRequest().getPath()))
            return chain.filter(exchange);

        HttpCookie accessTokenCookie = exchange.getRequest().getCookies().getFirst(COOKIE_ACCESS_TOKEN);
        if (accessTokenCookie == null || accessTokenCookie.getValue().isEmpty()) {
            return chain.filter(exchange);
        }

        String accessTokenValue = accessTokenCookie.getValue();
        return Mono.fromRunnable(() -> {
                    Jwts.parser()
                            .setSigningKey(secretKey)
                            .build()
                            .parseClaimsJws(accessTokenValue);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .then(chain.filter(exchange))
                .onErrorResume(RuntimeException.class, e -> {
                    return sendRedirect(exchange, Route.MAIN);
                });
    }

    private Mono<Void> sendRedirect(ServerWebExchange exchange, String url){
        exchange.getResponse().setStatusCode(HttpStatus.FOUND);
        exchange.getResponse().getHeaders().setLocation(URI.create(url));
        return exchange.getResponse().setComplete();
    }
}
