package petkovskimariobachelor.shoppingcartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petkovskimariobachelor.shoppingcartservice.entity.ProductItem;
import petkovskimariobachelor.shoppingcartservice.entity.ProductItemId;

public interface ProductItemRepository extends JpaRepository<ProductItem, ProductItemId> {
    ProductItem findByProductCode(String code);
}
