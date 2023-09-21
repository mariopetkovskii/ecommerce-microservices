package petkovskimariobachelor.orderservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.kafka.events.OrderEvent;
import petkovskimariobachelor.commonservice.kafka.events.OrderWithEmailEvent;
import petkovskimariobachelor.commonservice.shareddtos.shoppingcart.ShoppingCartSharedDto;
import petkovskimariobachelor.orderservice.entity.Order;
import petkovskimariobachelor.orderservice.infra.OrderEventPublisher;
import petkovskimariobachelor.orderservice.port.ShoppingCartPort;
import petkovskimariobachelor.orderservice.port.UserPort;
import petkovskimariobachelor.orderservice.repository.OrderRepository;
import petkovskimariobachelor.orderservice.request.OrderDto;
import petkovskimariobachelor.orderservice.service.interfaces.OrderService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartPort shoppingCartPort;
    private final UserPort userPort;
    private final OrderEventPublisher orderEventPublisher;

    @Override
    public String placeOrder(String token, OrderDto orderDto) {
        ShoppingCartSharedDto shoppingCartSharedDto = this.shoppingCartPort.getShoppingCart(token);
        Map<String, String> userDetails = this.userPort.getUserId(token);
        String userId = userDetails.get("userId");
        String email = userDetails.get("email");
        Order order = new Order.Builder()
                .userId(userId)
                .shoppingCartId(shoppingCartSharedDto.shoppingCartId())
                .deliveryAddress(orderDto.getDeliveryAddress())
                .totalPrice(shoppingCartSharedDto.total())
                .build();
        this.orderEventPublisher.publish(new OrderEvent(shoppingCartSharedDto.shoppingCartId()));
        this.orderEventPublisher.publish(new OrderWithEmailEvent(
                email,
                shoppingCartSharedDto.productList(),
                shoppingCartSharedDto.total(),
                orderDto.getDeliveryAddress()
        ));
        this.orderRepository.save(order);
        return "Successfully order";
    }
}
