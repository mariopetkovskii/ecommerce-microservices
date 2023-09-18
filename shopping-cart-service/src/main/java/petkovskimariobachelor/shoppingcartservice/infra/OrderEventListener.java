package petkovskimariobachelor.shoppingcartservice.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.kafka.DomainEvent;
import petkovskimariobachelor.commonservice.kafka.events.OrderEvent;
import petkovskimariobachelor.commonservice.kafka.topics.TopicType;
import petkovskimariobachelor.shoppingcartservice.service.interfaces.ShoppingCartService;

@Service
@RequiredArgsConstructor
public class OrderEventListener {
    private final ShoppingCartService shoppingCartService;
    @KafkaListener(topics = TopicType.TOPIC_ORDERED_SHOPPING_CART, groupId = "orderGroup")
    public void consumeOrder(String jsonMessage){
        try{
            OrderEvent orderEvent = DomainEvent.fromJson(jsonMessage, OrderEvent.class);
            this.shoppingCartService.deleteShoppingCartItems(orderEvent.getShoppingCart());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}