package pl.oskarinio.moneyisland.gateway.routes.config;

import org.springframework.http.HttpCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.server.authentication.ServerBearerTokenAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ReactiveCookieAuthenticationConverter extends ServerBearerTokenAuthenticationConverter {
    private final String COOKIE_ACCESS_TOKEN = "accessToken";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        HttpCookie accessTokenCookie = exchange.getRequest().getCookies().getFirst(COOKIE_ACCESS_TOKEN);

        if(accessTokenCookie == null || accessTokenCookie.getValue().isEmpty())
            return Mono.empty();

        String token = accessTokenCookie.getValue();
        return Mono.just(new BearerTokenAuthenticationToken(token));
    }
}
