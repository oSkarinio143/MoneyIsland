//package pl.oskarinio.moneyisland.gateway.config;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import pl.oskarinio.moneyisland.gateway.web.Route;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//
//
//@Component
//public class ReactiveAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
//
//}
//@Override
//public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException exception) {
//    exchange.getResponse().setStatusCode(HttpStatus.FOUND);
//
//    String loginUrl = Route.MAIN;
//    exchange.getResponse().getHeaders().setLocation(URI.create(loginUrl));
//
//    return exchange.getResponse().setComplete();
//}
