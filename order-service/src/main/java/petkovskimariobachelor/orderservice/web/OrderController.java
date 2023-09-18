package petkovskimariobachelor.orderservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petkovskimariobachelor.orderservice.request.OrderDto;
import petkovskimariobachelor.orderservice.service.interfaces.OrderService;

@RestController
@RequestMapping("/rest/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/placeOrder")
    public void placeOder(@RequestHeader("Authorization") String token, @RequestBody OrderDto orderDto){
        this.orderService.placeOrder(token, orderDto);
    }

}