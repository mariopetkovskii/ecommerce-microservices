package petkovskimariobachelor.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Routes {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/rest/user/**")
                        .and()
                        .uri("lb://user-service"))
                .route(r -> r.path("/rest/product/**")
                        .and()
                        .uri("lb://product-service"))
                .build();
    }
}
