package com.ecommerce.bootstrap.controllers;

import com.ecommerce.bootstrap.model.persistence.entities.Cart;
import com.ecommerce.bootstrap.model.persistence.entities.Product;
import com.ecommerce.bootstrap.model.persistence.entities.User;
import com.ecommerce.bootstrap.model.persistence.repositories.CartRepository;
import com.ecommerce.bootstrap.model.persistence.repositories.ProductRepository;
import com.ecommerce.bootstrap.model.persistence.repositories.UserRepository;
import com.ecommerce.bootstrap.model.requests.ModifyCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartRepository _cartRepository;

    @Autowired
    private ProductRepository _productRepository;
    @Autowired
    private UserRepository _userRepository;

    @PostMapping("/addToCart")
    public ResponseEntity<Cart> addToCart(@RequestBody ModifyCartRequest request){
        // get user associated with cart
        User user = _userRepository.findByUsername(request.getUsername());

        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        // find a product that matches the product id
        Optional<Product> product = _productRepository.findById(request.getProductId());

        Cart cart = user.getCart();

        IntStream.range(0, request.getQuantity())
                .forEach(i -> cart.addProduct(product.get()));

        _cartRepository.save(cart);

        return ResponseEntity.ok(cart);
    }

    @PostMapping("/removeToCart")
    public ResponseEntity<Cart>  removeFromCart(@RequestBody ModifyCartRequest request){
        // get user associated with cart
        User user = _userRepository.findByUsername(request.getUsername());

        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        // find a product that matches the product id
        Optional<Product> product = _productRepository.findById(request.getProductId());

        Cart cart = user.getCart();
        IntStream.range(0, request.getQuantity())
                .forEach(i -> cart.removeProduct(product.get()));

        _cartRepository.save(cart);

        return ResponseEntity.ok(cart);
    }


}
