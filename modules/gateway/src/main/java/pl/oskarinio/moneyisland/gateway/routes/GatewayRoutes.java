package pl.oskarinio.moneyisland.gateway.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {

    @Bean
    public RouteLocator authRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth", r -> r
                        .path("/oskarinio143/MoneyIsland/login/**",
                                "/oskarinio143/MoneyIsland/register/**",
                                "/oskarinio143/MoneyIsland/logout/**")
                        .filters(f -> f.stripPrefix(0))
                        .uri("http://localhost:8082"))

                .route("finance", r -> r
                        .path("/oskarinio143/MoneyIsland")
                        .filters(f -> f.stripPrefix(0))
                        .uri("http://localhost:8083"))
                .build();

    }

    @Bean
    public RouteLocator financeRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("finance", r -> r
                        .path("/oskarinio143/MoneyIsland")
                        .filters(f -> f.stripPrefix(0))
                        .uri("http://localhost:8083")).build();
    }


}
