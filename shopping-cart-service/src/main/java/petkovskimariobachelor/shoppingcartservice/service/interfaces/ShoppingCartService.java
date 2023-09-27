package petkovskimariobachelor.shoppingcartservice.service.interfaces;

import petkovskimariobachelor.commonservice.shareddtos.shoppingcart.ShoppingCartSharedDto;
import petkovskimariobachelor.shoppingcartservice.dto.ShoppingCartItemDto;

public interface ShoppingCartService {
    void addItemToShoppingCart(String userId, ShoppingCartItemDto shoppingCartItemDto);

    ShoppingCartSharedDto getShoppingCart(String userId);

    void deleteShoppingCartItems(String shoppingCartId);

    void deleteItemFromShoppingCart(String userId, ShoppingCartItemDto shoppingCartItemDto);
}
