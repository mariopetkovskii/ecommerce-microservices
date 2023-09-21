package petkovskimariobachelor.shoppingcartservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import petkovskimariobachelor.commonservice.shareddtos.shoppingcart.ShoppingCartSharedDto;
import petkovskimariobachelor.shoppingcartservice.dto.ShoppingCartItemDto;
import petkovskimariobachelor.shoppingcartservice.service.interfaces.ShoppingCartService;

@RestController
@RequestMapping("/rest/shopping-cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add-product")
    private void addProduct(@RequestHeader("Authorization") String header, @RequestBody ShoppingCartItemDto shoppingCartItemDto){
        this.shoppingCartService.addItemToShoppingCart(header, shoppingCartItemDto);
    }

    @GetMapping("/get-shopping-cart")
    private ShoppingCartSharedDto getShoppingCart(@RequestHeader("Authorization") String header){
        return this.shoppingCartService.getShoppingCart(header);
    }

    @PostMapping("/delete-item")
    private void deleteItemFromShoppingCart(@RequestHeader("Authorization") String header, @RequestBody ShoppingCartItemDto shoppingCartItemDto){
        this.shoppingCartService.deleteItemFromShoppingCart(header, shoppingCartItemDto);
    }

}
