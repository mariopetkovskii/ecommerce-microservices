package petkovskimariobachelor.shoppingcartservice.entity;

import lombok.NonNull;
import petkovskimariobachelor.commonservice.base.IdClass;

public class ProductItemId extends IdClass {
    private ProductItemId(){
        super(ProductItemId.randomId(ProductItemId.class).getId());
    }

    public ProductItemId(@NonNull String uuid) {
        super(uuid);
    }
}