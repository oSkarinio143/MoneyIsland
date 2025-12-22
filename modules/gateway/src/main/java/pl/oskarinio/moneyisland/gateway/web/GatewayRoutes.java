package pl.oskarinio.moneyisland.gateway.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {

    @Value("${auth.container:http://localhost:8082}")
    private String authUrl;

    @Value("${finance.container:http://localhost:8083}")
    private String financeUrl;

    @Bean
    public RouteLocator authRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth", r -> r
                        .path(Route.MAIN + Route.REGISTER,
                                Route.MAIN + Route.LOGIN,
                                Route.MAIN + Route.LOGOUT,
                                Route.MAIN + Route.ADMIN + "/**")
                        .filters(f -> f.stripPrefix(0))
                        .uri(authUrl))

                .route("finance", r -> r
                        .path(Route.MAIN + "/**")
                        .filters(f -> f.stripPrefix(0))
                        .uri(financeUrl))
                .build();
    }
}
