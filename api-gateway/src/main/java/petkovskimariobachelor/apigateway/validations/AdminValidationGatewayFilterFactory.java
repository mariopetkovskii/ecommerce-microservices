package petkovskimariobachelor.apigateway.validations;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AdminValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<AdminValidationGatewayFilterFactory.Config> {

    private final WebClient.Builder webClientBuilder;

    public AdminValidationGatewayFilterFactory(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String token = headers.getFirst(HttpHeaders.AUTHORIZATION);

            if (token == null || token.isEmpty()) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8082/rest/user/admin/validateToken")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .flatMap(isValid -> {
                        if(isValid){
                            return chain.filter(exchange);
                        }else {
                            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            return exchange.getResponse().setComplete();
                        }
                    });
        };
    }

    public static class Config {
    }
}