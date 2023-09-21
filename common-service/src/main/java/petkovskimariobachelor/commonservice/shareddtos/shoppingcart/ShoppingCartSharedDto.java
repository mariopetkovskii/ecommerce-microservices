package petkovskimariobachelor.commonservice.shareddtos.shoppingcart;

import petkovskimariobachelor.commonservice.shareddtos.product.ProductResponseSharedDto;

import java.util.List;

public record ShoppingCartSharedDto(String shoppingCartId, List<ProductResponseSharedDto> productList, Double total) {
}
