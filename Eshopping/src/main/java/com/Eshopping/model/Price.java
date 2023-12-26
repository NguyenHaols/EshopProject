package com.Eshopping.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    private Long price;
}
