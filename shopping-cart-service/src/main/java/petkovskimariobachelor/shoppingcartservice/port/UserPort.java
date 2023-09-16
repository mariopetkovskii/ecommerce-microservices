package petkovskimariobachelor.shoppingcartservice.port;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import petkovskimariobachelor.commonservice.shared.product.ProductSharedDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserPort {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public UserPort(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.serverUrl = "http://user-service";
    }
    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public Map<String, String> getUserId(String header) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(header.substring(7));

        Map<String, String> requestBody = new HashMap<>();
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ParameterizedTypeReference<Map<String, String>> responseType = new ParameterizedTypeReference<Map<String, String>>() {};

        ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                uri().path("/rest/user/user-id").build().toUri(),
                HttpMethod.POST,
                requestEntity,
                responseType
        );

        return response.getBody();
    }

}
