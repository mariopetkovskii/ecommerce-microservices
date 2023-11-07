package petkovskimariobachelor.shoppingcartservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import petkovskimariobachelor.commonservice.base.BaseEntity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
@Getter
public class ShoppingCart extends BaseEntity<ShoppingCartId> {
    private String userId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "shopping_cart_id")
    private Set<ProductItem> shoppingCart;


    public ShoppingCart(){
        super(ShoppingCartId.randomId(ShoppingCartId.class));
    }

    public static class Builder{
        ShoppingCart shoppingCart = new ShoppingCart();

        public Builder userId(String userId){
            shoppingCart.userId = userId;
            return this;
        }

        public Builder shoppingCart(){
            shoppingCart.shoppingCart = new HashSet<>();
            return this;
        }

        public ShoppingCart build(){
            return shoppingCart;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(userId, that.userId) && Objects.equals(shoppingCart, that.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, shoppingCart);
    }

    public void addItemToShoppingCart(ProductItem productItem){
        this.shoppingCart.add(productItem);
    }

    public void changeQuantityOfItem(ProductItem productItem, Integer amount){
        productItem.setQuantity(productItem.getQuantity() + amount);
    }

    public boolean checkIfItemExists(String code){
        return this.getShoppingCart()
                .stream()
                .anyMatch(item -> item.getProductCode().equals(code));
    }

    public ProductItem findProductItem(String productCode){
        return this.getShoppingCart()
                .stream().filter(item -> item.getProductCode().equals(productCode))
                .findFirst().get();
    }

}