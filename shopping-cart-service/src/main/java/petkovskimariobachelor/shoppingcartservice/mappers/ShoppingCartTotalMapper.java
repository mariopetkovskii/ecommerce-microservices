package petkovskimariobachelor.shoppingcartservice.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.shareddtos.product.ProductResponseSharedDto;
import petkovskimariobachelor.commonservice.shareddtos.product.ProductSharedDto;
import petkovskimariobachelor.commonservice.shareddtos.shoppingcart.ShoppingCartSharedDto;
import petkovskimariobachelor.shoppingcartservice.entity.ShoppingCart;
import petkovskimariobachelor.shoppingcartservice.port.ProductPort;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartTotalMapper implements Function<ShoppingCart, ShoppingCartSharedDto> {
    private final ProductPort productPort;
    @Override
    public ShoppingCartSharedDto apply(ShoppingCart shoppingCart) {
        Double total = shoppingCart.getShoppingCart()
                .stream().mapToDouble(product -> product.getQuantity() * product.getPrice())
                .sum();
        List<ProductResponseSharedDto> productList = shoppingCart.getShoppingCart()
                .stream()
                .map(productItem -> {
                    ProductSharedDto product = this.productPort.getProduct(productItem.getProductCode());
                    return new ProductResponseSharedDto(product.code(), product.price(), product.name(), productItem.getQuantity());
                })
                .collect(Collectors.toList());
        return new ShoppingCartSharedDto(
                shoppingCart.getId().getId(),
                productList,
                total
        );
    }
}