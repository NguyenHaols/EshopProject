package com.Eshopping.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "detail_order")
public class DetailOrder {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
