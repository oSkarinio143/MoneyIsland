package pl.oskarinio.moneyisland.gateway.config;

import org.springframework.http.HttpCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.web.server.authentication.ServerBearerTokenAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ReactiveCookieAuthenticationConverter extends ServerBearerTokenAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        HttpCookie accessTokenCookie = exchange.getRequest().getCookies().getFirst("accessToken");

        if(accessTokenCookie == null || accessTokenCookie.getValue().isEmpty())
            return Mono.empty();

        String token = accessTokenCookie.getValue();

        return Mono.just(new BearerTokenAuthenticationToken(token));
    }
}
