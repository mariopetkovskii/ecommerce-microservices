package petkovskimariobachelor.shoppingcartservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import petkovskimariobachelor.commonservice.base.BaseEntity;
import petkovskimariobachelor.commonservice.base.IdClass;

import java.util.Objects;

@Entity
@Table(name = "product_item")
@Getter
@Setter
public class ProductItem extends BaseEntity<ProductItemId> {
    private String productCode;
    private Integer quantity;
    private Double price;

    public ProductItem(@NonNull String productCode, @NonNull Integer quantity, Double price){
        super(IdClass.randomId(ProductItemId.class));
        this.productCode = productCode;
        this.quantity = quantity;
        this.price = price;
    }
    public ProductItem(){
        super(IdClass.randomId(ProductItemId.class));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem that = (ProductItem) o;
        return Objects.equals(productCode, that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode);
    }

    public void addQuantity(Integer amount){
        this.quantity = this.quantity + amount;
    }

}