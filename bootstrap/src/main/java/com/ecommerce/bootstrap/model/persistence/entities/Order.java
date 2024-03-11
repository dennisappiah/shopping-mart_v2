package com.ecommerce.bootstrap.model.persistence.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false, referencedColumnName = "id")
    @JsonProperty
    private User user;

    @ManyToMany
    @JsonProperty
    @Column
    private List<Product> products;

    @Column
    @JsonProperty
    private BigDecimal total;

    // method to create order from cart
    public static Order createFromCart(Cart cart) {
        Order order = new Order();

        order.setUser(cart.getUser());
        order.setTotal(cart.getTotal());
        order.setProducts(new ArrayList<>(cart.getProducts()));

        return order;
    }
}
