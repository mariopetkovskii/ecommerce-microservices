package petkovskimariobachelor.commonservice.kafka.events;

import lombok.Getter;
import petkovskimariobachelor.commonservice.kafka.DomainEvent;
import petkovskimariobachelor.commonservice.kafka.topics.TopicType;
import petkovskimariobachelor.commonservice.shareddtos.product.ProductResponseSharedDto;

import java.util.List;

@Getter
public class OrderWithEmailEvent extends DomainEvent {
    private String to;
    private List<ProductResponseSharedDto> productsList;
    private Double total;
    private String deliveryAddress;

    public OrderWithEmailEvent() {
        super(TopicType.TOPIC_ORDER_EMAIL);
    }

    public OrderWithEmailEvent(String to, List<ProductResponseSharedDto> productsList, Double total, String deliveryAddress) {
        super(TopicType.TOPIC_ORDER_EMAIL);
        this.to = to;
        this.productsList = productsList;
        this.total = total;
        this.deliveryAddress = deliveryAddress;
    }
}