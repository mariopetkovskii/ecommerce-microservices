package petkovskimariobachelor.shoppingcartservice.mappers;

import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.shared.product.ProductResponseSharedDto;
import petkovskimariobachelor.commonservice.shared.product.ProductSharedDto;
import petkovskimariobachelor.shoppingcartservice.entity.ProductItem;

import java.util.function.BiFunction;

@Service
public class ShoppingCartListingMapper implements BiFunction<ProductSharedDto, ProductItem, ProductResponseSharedDto> {
    @Override
    public ProductResponseSharedDto apply(ProductSharedDto product, ProductItem productItem) {
        return new ProductResponseSharedDto(
                product.code(),
                product.price(),
                product.name(),
                productItem.getQuantity()
        );
    }
}