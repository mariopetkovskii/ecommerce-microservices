package petkovskimariobachelor.orderservice.web;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petkovskimariobachelor.orderservice.request.OrderDto;
import petkovskimariobachelor.orderservice.service.interfaces.OrderService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/rest/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/placeOrder")
    @CircuitBreaker(name = "order", fallbackMethod = "fallbackMethod")
    public CompletableFuture<String> placeOder(@RequestHeader("X-Id") String userId,
                                               @RequestHeader("X-email") String email,
                                               @RequestBody OrderDto orderDto){
        return CompletableFuture.supplyAsync(() -> this.orderService.placeOrder(userId, email, orderDto));
    }

    public CompletableFuture<String> fallbackMethod(String userId,
                                                    String email,
                                                    OrderDto orderDto,
                                                    RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Something went wrong, try again later!");
    }
}