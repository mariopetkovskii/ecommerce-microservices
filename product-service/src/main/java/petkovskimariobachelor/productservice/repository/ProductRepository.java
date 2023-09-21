package petkovskimariobachelor.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import petkovskimariobachelor.productservice.entity.Product;
import petkovskimariobachelor.productservice.entity.ProductId;

public interface ProductRepository extends JpaRepository<Product, ProductId>, JpaSpecificationExecutor<Product> {
    Product findByCode(String code);
}
