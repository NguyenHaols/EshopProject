package com.Eshopping.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "promo")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "percent")
    private int percent;

    @Column(name = "day_start")
    private Date dayStart;

    @Column(name = "day_end")
    private Date dayEnd;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
