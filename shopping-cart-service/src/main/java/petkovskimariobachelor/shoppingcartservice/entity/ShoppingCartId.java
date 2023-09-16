package petkovskimariobachelor.shoppingcartservice.entity;

import lombok.NonNull;
import petkovskimariobachelor.commonservice.base.IdClass;

public class ShoppingCartId extends IdClass {
    private ShoppingCartId(){
        super(ShoppingCartId.randomId(ShoppingCartId.class).getId());
    }

    public ShoppingCartId(@NonNull String uuid){
        super(uuid);
    }

    public static ShoppingCartId of(String uuid){
        return new ShoppingCartId(uuid);
    }
}