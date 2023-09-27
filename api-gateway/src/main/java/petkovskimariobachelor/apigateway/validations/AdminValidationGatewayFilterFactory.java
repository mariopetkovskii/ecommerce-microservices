package petkovskimariobachelor.apigateway.validations;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AdminValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<AdminValidationGatewayFilterFactory.Config> {

    private final WebClient.Builder webClientBuilder;
    private final DiscoveryClient discoveryClient;

    public AdminValidationGatewayFilterFactory(WebClient.Builder webClientBuilder, DiscoveryClient discoveryClient) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
        this.discoveryClient = discoveryClient;
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

            String userServiceUrl = this.discoveryClient.getInstances("user-service")
                    .stream()
                    .findFirst()
                    .map(serviceInstance -> "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort())
                    .orElseThrow(() -> new RuntimeException("user-service not found in service registry"));

            return this.webClientBuilder.build()
                    .post()
                    .uri(userServiceUrl + "/rest/user/validateToken")
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