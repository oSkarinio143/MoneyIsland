package pl.oskarinio.moneyisland.gateway.routes.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pl.oskarinio.moneyisland.gateway.routes.controller.Route;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class ReactiveAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        exchange.getAttributes().put("adminErrorMessage", "Nie posiadasz uprawnień administratorskich");
        exchange.getResponse().setStatusCode(HttpStatus.FOUND);
        exchange.getResponse().getHeaders().setLocation(URI.create(Route.MAIN));

        return exchange.getResponse().setComplete();
    }
}