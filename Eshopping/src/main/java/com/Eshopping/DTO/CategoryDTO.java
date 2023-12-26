package com.Eshopping.DTO;


import com.Eshopping.model.DetailCategory;
import com.Eshopping.model.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private int id;
    private String categoryName;
    @JsonBackReference
    private List<ProductDTO> productList;
    @JsonManagedReference
    private List<DetailCategoryDTO> detailCategoryList;
}
