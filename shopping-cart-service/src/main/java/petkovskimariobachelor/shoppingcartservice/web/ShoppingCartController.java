package petkovskimariobachelor.shoppingcartservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petkovskimariobachelor.commonservice.shareddtos.shoppingcart.ShoppingCartSharedDto;
import petkovskimariobachelor.shoppingcartservice.dto.ShoppingCartItemDto;
import petkovskimariobachelor.shoppingcartservice.service.interfaces.ShoppingCartService;

import java.util.Map;

@RestController
@RequestMapping("/rest/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add-product")
    private void addProduct(@RequestHeader("X-id") String userId, @RequestBody ShoppingCartItemDto shoppingCartItemDto, @RequestHeader Map<String, String> headers){
        this.shoppingCartService.addItemToShoppingCart(userId, shoppingCartItemDto);
    }

    @GetMapping("/get-shopping-cart")
    private ShoppingCartSharedDto getShoppingCart(@RequestHeader("X-id") String userId){
        return this.shoppingCartService.getShoppingCart(userId);
    }

    @PostMapping("/delete-item")
    private void deleteItemFromShoppingCart(@RequestHeader("X-id") String userId, @RequestBody ShoppingCartItemDto shoppingCartItemDto){
        this.shoppingCartService.deleteItemFromShoppingCart(userId, shoppingCartItemDto);
    }

}
