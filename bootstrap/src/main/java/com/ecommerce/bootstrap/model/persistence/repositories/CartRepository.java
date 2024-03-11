package com.ecommerce.bootstrap.model.persistence.repositories;

import com.ecommerce.bootstrap.model.persistence.entities.Cart;
import com.ecommerce.bootstrap.model.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
