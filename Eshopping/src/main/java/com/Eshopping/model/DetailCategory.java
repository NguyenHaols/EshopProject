package com.Eshopping.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "detail_category")
public class DetailCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column (name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "detailCategory")
    private List<Product> productList;
}
