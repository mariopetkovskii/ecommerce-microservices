package petkovskimariobachelor.shoppingcartservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.shared.product.ProductSharedDto;
import petkovskimariobachelor.commonservice.shared.shoppingcart.ShoppingCartSharedDto;
import petkovskimariobachelor.shoppingcartservice.dto.ShoppingCartItemDto;
import petkovskimariobachelor.shoppingcartservice.entity.ProductItem;
import petkovskimariobachelor.shoppingcartservice.entity.ShoppingCart;
import petkovskimariobachelor.shoppingcartservice.mappers.ShoppingCartTotalMapper;
import petkovskimariobachelor.shoppingcartservice.port.ProductPort;
import petkovskimariobachelor.shoppingcartservice.port.UserPort;
import petkovskimariobachelor.shoppingcartservice.repository.ProductItemRepository;
import petkovskimariobachelor.shoppingcartservice.repository.ShoppingCartRepository;
import petkovskimariobachelor.shoppingcartservice.service.interfaces.ShoppingCartService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImplementation implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductItemRepository productItemRepository;
    private final UserPort userPort;
    private final ProductPort productPort;
    private final ShoppingCartTotalMapper shoppingCartTotalMapper;
    @Override
    public void addItemToShoppingCart(String header, ShoppingCartItemDto shoppingCartItemDto) {
        Map<String, String> userDetails = this.userPort.getUserId(header);
        ShoppingCart shoppingCart = this.findOrCreateShoppingCart(userDetails.get("userId"));
        ProductSharedDto product = this.productPort.getProduct(shoppingCartItemDto.getProductCode());
        ProductItem productItem = new ProductItem(shoppingCartItemDto.getProductCode(), shoppingCartItemDto.getQuantity(), product.price());
        if(shoppingCart.getShoppingCart().contains(productItem)){
            shoppingCart.changeQuantityOfItem(productItem, shoppingCartItemDto.getQuantity());
        }else{
            shoppingCart.addItemToShoppingCart(productItem);
        }

        this.shoppingCartRepository.saveAndFlush(shoppingCart);
    }

    @Override
    public ShoppingCartSharedDto getShoppingCart(String header) {
        Map<String, String> userDetails = this.userPort.getUserId(header);
        ShoppingCart shoppingCart = this.shoppingCartRepository.findByUserId(userDetails.get("userId")).orElseThrow(RuntimeException::new);
        return this.shoppingCartTotalMapper.apply(shoppingCart);
    }

    @Override
    public void deleteShoppingCartItems(String header, String shoppingCartId) {
        Map<String, String> userDetails = this.userPort.getUserId(header);
        ShoppingCart shoppingCart = this.shoppingCartRepository.findByUserId(userDetails.get("userId")).orElseThrow(RuntimeException::new);
        shoppingCart.getShoppingCart().clear();
        this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteItemFromShoppingCart(String header, ShoppingCartItemDto shoppingCartItemDto) {
        Map<String, String> userDetails = this.userPort.getUserId(header);
        ShoppingCart shoppingCart = this.shoppingCartRepository.findByUserId(userDetails.get("userId")).orElseThrow(RuntimeException::new);
        ProductItem productItem = this.productItemRepository.findByProductCode(shoppingCartItemDto.getProductCode());
        shoppingCart.getShoppingCart().remove(productItem);
        this.shoppingCartRepository.saveAndFlush(shoppingCart);
    }

    private ShoppingCart findOrCreateShoppingCart(String userId) {
        return shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    ShoppingCart newShoppingCart = new ShoppingCart.Builder()
                            .userId(userId)
                            .shoppingCart()
                            .build();
                    return shoppingCartRepository.save(newShoppingCart);
                });
    }
}
