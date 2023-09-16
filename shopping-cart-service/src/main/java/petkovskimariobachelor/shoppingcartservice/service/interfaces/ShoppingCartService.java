package petkovskimariobachelor.shoppingcartservice.service.interfaces;

import petkovskimariobachelor.commonservice.shared.product.ProductSharedDto;
import petkovskimariobachelor.commonservice.shared.shoppingcart.ShoppingCartSharedDto;
import petkovskimariobachelor.shoppingcartservice.dto.ShoppingCartItemDto;

public interface ShoppingCartService {
    void addItemToShoppingCart(String header, ShoppingCartItemDto shoppingCartItemDto);

    ShoppingCartSharedDto getShoppingCart(String header);

    void deleteShoppingCartItems(String header, String shoppingCartId);

    void deleteItemFromShoppingCart(String header, ShoppingCartItemDto shoppingCartItemDto);
}
