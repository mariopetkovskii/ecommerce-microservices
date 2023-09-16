package petkovskimariobachelor.productservice.mappers;

import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.shared.product.ProductSharedDto;
import petkovskimariobachelor.productservice.entity.Product;

import java.util.function.Function;

@Service
public class ProductResponseDtoMapper implements Function<Product, ProductSharedDto> {
    @Override
    public ProductSharedDto apply(Product product) {
        return new ProductSharedDto(
                product.getCode(),
                product.getName(),
                product.getBrand(),
                product.getPrice()
        );
    }
}
