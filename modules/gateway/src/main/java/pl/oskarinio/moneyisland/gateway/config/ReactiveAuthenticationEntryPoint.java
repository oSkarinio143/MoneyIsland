package pl.oskarinio.moneyisland.gateway.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pl.oskarinio.moneyisland.gateway.web.Route;
import reactor.core.publisher.Mono;

import java.net.URI;


@Component
public class ReactiveAuthenticationEntryPoint implements ServerAuthenticationEntryPoint{

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        return redirectToMain(exchange);
    }

    private Mono<Void> redirectToMain(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.FOUND);
        exchange.getResponse().getHeaders().setLocation(URI.create(Route.MAIN));
        return exchange.getResponse().setComplete();
    }
}
