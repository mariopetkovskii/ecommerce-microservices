package petkovskimariobachelor.commonservice.shared.shoppingcart;

import petkovskimariobachelor.commonservice.shared.product.ProductResponseSharedDto;

import java.util.List;

public record ShoppingCartSharedDto(String shoppingCartId, List<ProductResponseSharedDto> productList, Double total) {
}
