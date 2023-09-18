package petkovskimariobachelor.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import petkovskimariobachelor.commonservice.base.BaseEntity;

@Entity
@Table(name = "orders")
@Getter
public class Order extends BaseEntity<OrderId> {
    private String userId;
    private String shoppingCartId;
    private Double totalPrice;
    private String deliveryAddress;

    public Order(){
        super(OrderId.randomId(OrderId.class));
    }

    public static class Builder{
        Order order = new Order();

        public Builder userId(String userId){
            order.userId = userId;
            return this;
        }

        public Builder shoppingCartId(String shoppingCartId){
            order.shoppingCartId = shoppingCartId;
            return this;
        }

        public Builder totalPrice(Double totalPrice){
            order.totalPrice = totalPrice;
            return this;
        }

        public Builder deliveryAddress(String deliveryAddress){
            order.deliveryAddress = deliveryAddress;
            return this;
        }

        public Order build(){
            return order;
        }
    }
}