package uz.pdp.vazifa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.vazifa2.entity.Cart;
import uz.pdp.vazifa2.entity.Product;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query(value = "select p.id, p.name, p.description, p.price, p.category_id, p.brand_id  from product p\n" +
            "    join cart_products on p.id = cart_products.products_id\n" +
            "    join cart on cart_products.cart_id = cart.id\n" +
            "    join users u on cart.user_id = u.id where u.id =:userId", nativeQuery = true)
    List<Product> findQuery(Integer userId);

}
