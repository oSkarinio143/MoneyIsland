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
                .route("main", r -> r
                        .path("/login/**", "/user/**", "/battle/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8081")).build();
    }
}
