package pl.oskarinio.moneyisland.gateway.routes.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import pl.oskarinio.moneyisland.gateway.routes.controller.Route;
import pl.oskarinio.moneyisland.gateway.routes.filter.WebCspNonceFilter;
import pl.oskarinio.moneyisland.gateway.routes.filter.WebExpiredTokenFilter;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Base64;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         ReactiveJwtDecoder reactiveJwtDecoder,
                                                         ReactiveCookieAuthenticationConverter reactiveCookieAuthenticationConverter,
                                                         ReactiveAuthenticationEntryPoint reactiveAuthenticationEntryPoint,
                                                         ReactiveAccessDeniedHandler reactiveAccessDeniedHandler,
                                                         WebCspNonceFilter webCspNonceFilter,
                                                         WebExpiredTokenFilter webExpiredTokenFilter
                                                         ){
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon.ico").permitAll()
                        .pathMatchers(Route.MAIN + Route.LOGIN,
                                Route.MAIN + Route.REGISTER,
                                Route.MAIN).permitAll()
                        .pathMatchers(Route.MAIN + Route.USER + "/**").hasRole("USER")
                        .pathMatchers(Route.MAIN + Route.ADMIN + "/**").hasRole("ADMIN")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtDecoder(reactiveJwtDecoder))
                        .bearerTokenConverter(reactiveCookieAuthenticationConverter)
                        .accessDeniedHandler(reactiveAccessDeniedHandler)
                        .authenticationEntryPoint(reactiveAuthenticationEntryPoint)
                )
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .headers(headers -> headers
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives("default-src 'self'; " +
                                        "style-src 'self' 'nonce-{nonce}';" +
                                        "script-src 'self' 'nonce-{nonce}';" +
                                        "img-src 'self' data: https://oskarinio143.github.io; " +
                                        "font-src 'self' https://cdnjs.cloudflare.com; " +
                                        "frame-ancestors 'none'; " +
                                        "form-action 'self'; " +
                                        "object-src 'none';")
                        )
                        .contentTypeOptions(Customizer.withDefaults())
                        .frameOptions(ServerHttpSecurity.HeaderSpec.FrameOptionsSpec::disable)
                        .hsts(hstsSpec -> hstsSpec
                                .maxAge(java.time.Duration.ofDays(365))
                                .includeSubdomains(true)
                                .preload(true)
                        )
                )
                .addFilterBefore(webExpiredTokenFilter, SecurityWebFiltersOrder.FIRST)
                .addFilterBefore(webCspNonceFilter, SecurityWebFiltersOrder.HTTP_HEADERS_WRITER);
        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder(@Value("${jwt.secret.base64}") String secretKeyString) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyString));
        NimbusJwtDecoder blockingDecoder = NimbusJwtDecoder.withSecretKey(secretKey).build();

        return token -> {
            try {
                return Mono.fromCallable(() -> blockingDecoder.decode(token));
            } catch (JwtException e) {
                return Mono.error(e);
            }
        };
    }

    //To chyba do zmiany
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }
}
