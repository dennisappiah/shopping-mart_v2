package com.ecommerce.bootstrap.controllers;

import com.ecommerce.bootstrap.model.persistence.entities.Cart;
import com.ecommerce.bootstrap.model.persistence.entities.User;
import com.ecommerce.bootstrap.model.persistence.repositories.CartRepository;
import com.ecommerce.bootstrap.model.persistence.repositories.UserRepository;
import com.ecommerce.bootstrap.model.requests.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usr")
public class UserController {
    private static final int PASSWORD_MINIMUM_SIZE = 7;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private CartRepository _cartRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        Optional<User> user = _userRepository.findById(id);

        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        User user = _userRepository.findByUsername(username);

        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest){
        User user = new User();

        Cart cart = new Cart();

        _cartRepository.save(cart);

        user.setCart(cart);
        user.setUsername(createUserRequest.getUsername());

        if(createUserRequest.getPassword().length() < PASSWORD_MINIMUM_SIZE ||
        !createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())){
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));

        _userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}
