package petkovskimariobachelor.shoppingcartservice.service.interfaces;

import petkovskimariobachelor.commonservice.shareddtos.shoppingcart.ShoppingCartSharedDto;
import petkovskimariobachelor.shoppingcartservice.dto.ShoppingCartItemDto;

public interface ShoppingCartService {
    void addItemToShoppingCart(String header, ShoppingCartItemDto shoppingCartItemDto);

    ShoppingCartSharedDto getShoppingCart(String header);

    void deleteShoppingCartItems(String shoppingCartId);

    void deleteItemFromShoppingCart(String header, ShoppingCartItemDto shoppingCartItemDto);
}
