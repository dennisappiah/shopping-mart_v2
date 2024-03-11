package com.ecommerce.bootstrap.model.persistence.repositories;

import com.ecommerce.bootstrap.model.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
