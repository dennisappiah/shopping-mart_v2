package com.ecommerce.bootstrap.controllers;

import com.ecommerce.bootstrap.model.persistence.entities.Order;
import com.ecommerce.bootstrap.model.persistence.entities.User;
import com.ecommerce.bootstrap.model.persistence.repositories.OrderRepository;
import com.ecommerce.bootstrap.model.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepository _orderRepository;

    @Autowired
    private UserRepository _userRepository;
    @PostMapping("/submit/{username}")
    public ResponseEntity<Order> submitOrder(@PathVariable String username){
        // get user associated with the order
        User user = _userRepository.findByUsername(username);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        Order order = Order.createFromCart(user.getCart());

        return ResponseEntity.ok(order);
    }


    @GetMapping("/history/{username}")
    public ResponseEntity<List<Order>> getOrdersForUser(@PathVariable String username) {
        User user = _userRepository.findByUsername(username);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(_orderRepository.findByUser(user));
    }

}
