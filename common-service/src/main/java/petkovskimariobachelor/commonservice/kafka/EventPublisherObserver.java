package petkovskimariobachelor.commonservice.kafka;

public interface EventPublisherObserver {
    void publish(DomainEvent event);
}
