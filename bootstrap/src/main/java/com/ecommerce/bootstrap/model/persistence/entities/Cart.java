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
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @Column
    private Long id;

    @OneToOne(mappedBy = "cart")
    @JsonProperty
    private User user;

    @ManyToMany
    @JsonProperty
    @Column
    private List<Product> products;

    @Column
    @JsonProperty
    private BigDecimal total;

    public void addProduct(Product product){
        if(products == null)
            products = new ArrayList<>();

        products.add(product);

        if(total == null)
            total = new BigDecimal(0);

        total = total.add(product.getPrice());
    }

    public void removeProduct(Product product){
        if(products == null)
            products = new ArrayList<>();

        products.remove(product);

        if(total == null)
            total = new BigDecimal(0);

        total = total.subtract(product.getPrice());
    }
}
