package petkovskimariobachelor.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petkovskimariobachelor.productservice.entity.Product;
import petkovskimariobachelor.productservice.entity.ProductId;

public interface ProductRepository extends JpaRepository<Product, ProductId> {
    Product findByCode(String code);
}
