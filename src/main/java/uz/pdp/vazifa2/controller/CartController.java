package uz.pdp.vazifa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa2.service.CartService;
import uz.pdp.vazifa2.service.UserService;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCart(@PathVariable Integer userId){
        boolean byUserId = userService.existByUserId(userId);
        if (!byUserId)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");

        return ResponseEntity.status(200).body(cartService.getAllProducts(userId));
    }

    @PostMapping("/{cardId}")
    public ResponseEntity<?> addProductToCart(@PathVariable Integer cardId){
        cartService.addProductToCart(cardId);
    }

}
