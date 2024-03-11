package com.ecommerce.bootstrap.model.persistence.repositories;

import com.ecommerce.bootstrap.model.persistence.entities.Order;
import com.ecommerce.bootstrap.model.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
