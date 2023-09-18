package petkovskimariobachelor.orderservice.service.interfaces;

import petkovskimariobachelor.orderservice.request.OrderDto;

public interface OrderService {
    String placeOrder(String token, OrderDto orderDto);
}
