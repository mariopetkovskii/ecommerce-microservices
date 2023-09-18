package petkovskimariobachelor.notificationservice.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.kafka.events.OrderWithEmailEvent;
import petkovskimariobachelor.commonservice.kafka.topics.TopicType;
import petkovskimariobachelor.notificationservice.service.EmailService;

@Service
public class EmailEventListener {
    @KafkaListener(topics = TopicType.TOPIC_ORDER_EMAIL, groupId = "orderEmailGroup")
    public void consumeOrder(String jsonMessage){
        try{
            OrderWithEmailEvent order = OrderWithEmailEvent.fromJson(jsonMessage, OrderWithEmailEvent.class);
            EmailService.sendOrderEmail(order.getTo(), order.getProductsList(), order.getTotal(), order.getDeliveryAddress());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
