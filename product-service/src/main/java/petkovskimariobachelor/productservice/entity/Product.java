package petkovskimariobachelor.productservice.entity;

import petkovskimariobachelor.commonservice.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "products")
@Getter
public class Product extends BaseEntity<ProductId> {
    private String code;
    private String name;
    private String brand;
    private Double price;

    public Product(){
        super(ProductId.randomId(ProductId.class));
    }
    public static class Builder{
        Product product = new Product();
        public Builder code(String code){
            product.code = code;
            return this;
        }

        public Builder name(String name){
            product.name = name;
            return this;
        }

        public Builder brand(String brand){
            product.brand = brand;
            return this;
        }

        public Builder price(Double price){
            product.price = price;
            return this;
        }

        public Product build(){
            return product;
        }
    }

}