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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "detail_Category_id")
    private DetailCategory detailCategory;

    @OneToMany(mappedBy = "product")
    private List<Promo> promoList;

    @OneToMany(mappedBy = "product")
    private List<RatingStar> ratingStarList;

    @OneToMany(mappedBy = "product")
    private List<DetailOrder> detailOrderList;

    @OneToMany(mappedBy = "product")
    private List<Price> priceList;


}
