package pl.oskarinio.moneyisland.gateway.filter;

import org.springframework.http.HttpHeaders;
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

        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(builder -> builder.header("X-CSP-Nonce", nonce))
                .build();

        // Rejestrujemy akcję, która wykona się tuż przed wysłaniem odpowiedzi
        exchange.getResponse().beforeCommit(() -> {
            // W tym momencie inne filtry już mogły dodać nagłówek CSP z placeholderem
            HttpHeaders headers = exchange.getResponse().getHeaders();
            String cspHeader = headers.getFirst("Content-Security-Policy");

            if (cspHeader != null && cspHeader.contains("{nonce}")) {
                final String finalCsp = cspHeader.replace("{nonce}", nonce);
                // Ta modyfikacja jest teraz bezpieczna!
                headers.set("Content-Security-Policy", finalCsp);
            }

            // beforeCommit musi zwrócić Mono<Void>
            return Mono.empty();
        });
        // Przekazujemy żądanie dalej w łańcuchu
        return chain.filter(modifiedExchange);
    }
}
