package com.Eshopping.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ratingStar")
public class RatingStar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "feedback")
    private String feedback;

    @Column(name = "rate_point")
    private int ratePoint;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
