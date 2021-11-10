package uz.pdp.vazifa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.vazifa2.entity.Cart;
import uz.pdp.vazifa2.entity.Product;
import uz.pdp.vazifa2.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    
}
