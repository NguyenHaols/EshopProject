package com.Eshopping.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "img_1")
    private String img1;

    @Column(name = "img_2")
    private String img2;

    @Column(name = "img_3")
    private String img3;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Promo> promoList;

    @OneToMany(mappedBy = "product")
    private List<RatingStar> ratingStarList;

    @OneToMany(mappedBy = "product")
    private List<DetailOrder> detailOrderList;
}
