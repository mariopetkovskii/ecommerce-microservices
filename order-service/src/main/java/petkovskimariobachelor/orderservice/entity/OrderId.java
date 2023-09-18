package petkovskimariobachelor.orderservice.entity;

import lombok.NonNull;
import petkovskimariobachelor.commonservice.base.IdClass;

public class OrderId extends IdClass {
    private OrderId(){
        super(OrderId.randomId(OrderId.class).getId());
    }

    public OrderId(@NonNull String uuid){
        super(uuid);
    }

    public static OrderId of(String uuid){
        return new OrderId(uuid);
    }
}