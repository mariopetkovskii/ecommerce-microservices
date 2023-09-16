package petkovskimariobachelor.shoppingcartservice.port;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import petkovskimariobachelor.commonservice.shared.product.ProductSharedDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductPort {
    private final RestTemplate restTemplate;
    private final String server;

    public ProductPort(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.server = "http://product-service";
    }

    private UriComponentsBuilder uri(){
        return UriComponentsBuilder.fromUriString(this.server);
    }

    public ProductSharedDto getProduct(String productCode){
        Map<String, String> map = new HashMap<>();
        map.put("code", productCode);
        return this.restTemplate.postForObject(uri().path("/rest/product/get-product").build().toUri(), map, ProductSharedDto.class);
    }
}
