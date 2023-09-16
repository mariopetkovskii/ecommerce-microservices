package petkovskimariobachelor.shoppingcartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petkovskimariobachelor.shoppingcartservice.entity.ShoppingCart;
import petkovskimariobachelor.shoppingcartservice.entity.ShoppingCartId;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId> {
    Optional<ShoppingCart> findByUserId(String userId);
}
