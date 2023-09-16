package petkovskimariobachelor.productservice.entity;

import petkovskimariobachelor.commonservice.base.IdClass;
import lombok.NonNull;

public class ProductId extends IdClass {
    private ProductId(){
        super(ProductId.randomId(ProductId.class).getId());
    }

    public ProductId(@NonNull String uuid){
        super(uuid);
    }

    public static ProductId of(String uuid){
        return new ProductId(uuid);
    }
}