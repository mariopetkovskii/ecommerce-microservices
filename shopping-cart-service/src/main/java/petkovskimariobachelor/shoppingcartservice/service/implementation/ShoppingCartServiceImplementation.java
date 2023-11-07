package petkovskimariobachelor.shoppingcartservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import petkovskimariobachelor.commonservice.shareddtos.product.ProductSharedDto;
import petkovskimariobachelor.commonservice.shareddtos.shoppingcart.ShoppingCartSharedDto;
import petkovskimariobachelor.shoppingcartservice.dto.ShoppingCartItemDto;
import petkovskimariobachelor.shoppingcartservice.entity.ProductItem;
import petkovskimariobachelor.shoppingcartservice.entity.ShoppingCart;
import petkovskimariobachelor.shoppingcartservice.entity.ShoppingCartId;
import petkovskimariobachelor.shoppingcartservice.helpers.ShoppingCartFunctions;
import petkovskimariobachelor.shoppingcartservice.mappers.ShoppingCartTotalMapper;
import petkovskimariobachelor.shoppingcartservice.port.ProductPort;
import petkovskimariobachelor.shoppingcartservice.repository.ProductItemRepository;
import petkovskimariobachelor.shoppingcartservice.repository.ShoppingCartRepository;
import petkovskimariobachelor.shoppingcartservice.service.interfaces.ShoppingCartService;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImplementation implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductItemRepository productItemRepository;
    private final ProductPort productPort;
    private final ShoppingCartTotalMapper shoppingCartTotalMapper;
    @Override
    public void addItemToShoppingCart(String userId, ShoppingCartItemDto shoppingCartItemDto) {
        ShoppingCart shoppingCart = this.findOrCreateShoppingCart(userId);
        ProductSharedDto product = this.productPort.getProduct(shoppingCartItemDto.getProductCode());
        if(shoppingCart.checkIfItemExists(shoppingCartItemDto.getProductCode())){
            ProductItem productItem = shoppingCart.findProductItem(shoppingCartItemDto.getProductCode());
            shoppingCart.changeQuantityOfItem(productItem, shoppingCartItemDto.getQuantity());
        }else{
            shoppingCart.addItemToShoppingCart(new ProductItem(shoppingCartItemDto.getProductCode(), shoppingCartItemDto.getQuantity(), product.price()));
        }
        this.shoppingCartRepository.saveAndFlush(shoppingCart);
    }

    @Override
    public ShoppingCartSharedDto getShoppingCart(String userId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findByUserId(userId).orElseThrow(RuntimeException::new);
        return this.shoppingCartTotalMapper.apply(shoppingCart);
    }

    @Override
    public void deleteShoppingCartItems(String shoppingCartId) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findById(ShoppingCartId.of(shoppingCartId))
                        .orElseThrow();
        shoppingCart.getShoppingCart().clear();
        this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteItemFromShoppingCart(String userId, ShoppingCartItemDto shoppingCartItemDto) {
        ShoppingCart shoppingCart = this.shoppingCartRepository.findByUserId(userId).orElseThrow(RuntimeException::new);
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
