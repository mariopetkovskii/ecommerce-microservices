package petkovskimariobachelor.productservice.mappers;

import org.springframework.stereotype.Service;
import petkovskimariobachelor.productservice.entity.Product;
import petkovskimariobachelor.productservice.response.ProductResponseDto;

import java.util.function.Function;

@Service
public class ProductResponseDtoMapper implements Function<Product, ProductResponseDto> {
    @Override
    public ProductResponseDto apply(Product product) {
        return new ProductResponseDto(
                product.getCode(),
                product.getName(),
                product.getBrand(),
                product.getPrice()
        );
    }
}
