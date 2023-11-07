package petkovskimariobachelor.shoppingcartservice.helpers;

import petkovskimariobachelor.shoppingcartservice.entity.ProductItem;
import petkovskimariobachelor.shoppingcartservice.entity.ShoppingCart;

public class ShoppingCartFunctions {

    public static boolean checkIfItemExists(ShoppingCart shoppingCart, String code){
        return shoppingCart.getShoppingCart()
                .stream()
                .anyMatch(item -> item.getProductCode().equals(code));
    }

    public static ProductItem findProductItem(ShoppingCart shoppingCart, String productCode){
        return shoppingCart.getShoppingCart()
                .stream().filter(item -> item.getProductCode().equals(productCode))
                .findFirst().get();
    }
}
