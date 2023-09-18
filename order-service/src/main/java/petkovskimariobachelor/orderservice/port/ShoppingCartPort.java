package petkovskimariobachelor.orderservice.port;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import petkovskimariobachelor.commonservice.shared.shoppingcart.ShoppingCartSharedDto;

@Service
public class ShoppingCartPort {
    private final RestTemplate restTemplate;
    private final String server;
    public ShoppingCartPort(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.server = "http://shopping-cart-service";
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.server);
    }

    public ShoppingCartSharedDto getShoppingCart(String bearerToken){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", bearerToken);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<ShoppingCartSharedDto> response = restTemplate.exchange(this.server + "/rest/shopping-cart/get-shopping-cart", HttpMethod.GET, entity, ShoppingCartSharedDto.class);
        return response.getBody();
    }
}