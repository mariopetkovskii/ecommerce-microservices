package petkovskimariobachelor.commonservice.kafka.events;

import lombok.Getter;
import petkovskimariobachelor.commonservice.kafka.DomainEvent;
import petkovskimariobachelor.commonservice.kafka.topics.TopicType;

@Getter
public class OrderEvent extends DomainEvent {
    private String shoppingCart;
    public OrderEvent(){
        super(TopicType.TOPIC_ORDERED_SHOPPING_CART);
    }
    public OrderEvent(String shoppingCart){
        super(TopicType.TOPIC_ORDERED_SHOPPING_CART);
        this.shoppingCart = shoppingCart;
    }
}
