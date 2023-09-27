package petkovskimariobachelor.orderservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.kafka.events.OrderEvent;
import petkovskimariobachelor.commonservice.kafka.events.OrderWithEmailEvent;
import petkovskimariobachelor.commonservice.shareddtos.shoppingcart.ShoppingCartSharedDto;
import petkovskimariobachelor.orderservice.entity.Order;
import petkovskimariobachelor.orderservice.infra.OrderEventPublisher;
import petkovskimariobachelor.orderservice.port.ShoppingCartPort;
import petkovskimariobachelor.orderservice.repository.OrderRepository;
import petkovskimariobachelor.orderservice.request.OrderDto;
import petkovskimariobachelor.orderservice.service.interfaces.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartPort shoppingCartPort;
    private final OrderEventPublisher orderEventPublisher;

    @Override
    public String placeOrder(String userId, String email, OrderDto orderDto) {
        ShoppingCartSharedDto shoppingCartSharedDto = this.shoppingCartPort.getShoppingCart(userId);
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
