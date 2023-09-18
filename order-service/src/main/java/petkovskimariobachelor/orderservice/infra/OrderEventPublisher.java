package petkovskimariobachelor.orderservice.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.kafka.DomainEvent;
import petkovskimariobachelor.commonservice.kafka.EventPublisherObserver;

@Service
@RequiredArgsConstructor
public class OrderEventPublisher implements EventPublisherObserver {
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public void publish(DomainEvent event) {
        this.kafkaTemplate.send(event.topic(), event.toJson());
    }
}
