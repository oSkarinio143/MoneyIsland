package pl.oskarinio.moneyisland.gateway;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class WebCspNonceFilter implements WebFilter {

    private static final int NONCE_SIZE = 32;
    public static final String CSP_NONCE_ATTRIBUTE = "cspNonce";
    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        byte[] nonceArray = new byte[NONCE_SIZE];
        secureRandom.nextBytes(nonceArray);
        String nonce = Base64.getEncoder().encodeToString(nonceArray);

        exchange.getAttributes().put(CSP_NONCE_ATTRIBUTE, nonce);

        return chain.filter(exchange)
                .doOnSuccess(aVoid -> {
                    ServerHttpResponse response = exchange.getResponse();
                    String cspHeader = response.getHeaders().getFirst("Content-Security-Policy");

                    if(cspHeader != null){
                        final String finalCsp = cspHeader.replace("{nonce}", nonce);
                        response.getHeaders().set("Content-Security-Policy",finalCsp);
                    }
                });
    }
}
