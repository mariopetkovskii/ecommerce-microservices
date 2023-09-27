package petkovskimariobachelor.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import petkovskimariobachelor.apigateway.validations.AdminValidationGatewayFilterFactory;
import petkovskimariobachelor.apigateway.validations.TokenValidationGatewayFilterFactory;
import petkovskimariobachelor.commonservice.shareddtos.TokenValidationResponse;
import reactor.core.publisher.Mono;

@Configuration
public class Routes {
    private final TokenValidationGatewayFilterFactory tokenValidationGatewayFilterFactory;
    private final AdminValidationGatewayFilterFactory adminValidationGatewayFilterFactory;

    public Routes(TokenValidationGatewayFilterFactory tokenValidationGatewayFilterFactory, AdminValidationGatewayFilterFactory adminValidationGatewayFilterFactory) {
        this.tokenValidationGatewayFilterFactory = tokenValidationGatewayFilterFactory;
        this.adminValidationGatewayFilterFactory = adminValidationGatewayFilterFactory;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/rest/user/**")
                        .and()
                        .uri("lb://user-service"))
                .route(r -> r.path("/rest/product/admin/**")
                        .filters(f -> f.filter(adminValidationGatewayFilterFactory.apply(new AdminValidationGatewayFilterFactory.Config())))
                        .uri("lb://product-service"))
                .route(r -> r.path("/rest/product/**")
                        .filters(f -> f.filter(tokenValidationGatewayFilterFactory.apply(new TokenValidationGatewayFilterFactory.Config())))
                        .uri("lb://product-service"))
                .route(r -> r.path("/rest/shopping-cart/**")
                        .filters(f -> f.filter(tokenValidationGatewayFilterFactory.apply(new TokenValidationGatewayFilterFactory.Config())))
                        .uri("lb://shopping-cart-service"))
                .route(r -> r.path("/rest/order/**")
                        .filters(f -> f.filter(tokenValidationGatewayFilterFactory.apply(new TokenValidationGatewayFilterFactory.Config())))
                        .uri("lb://order-service"))
                .build();
    }
}
