package com.Eshopping.DTO;

import com.Eshopping.model.Category;
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
public class DetailCategoryDTO {
    int id;

    @JsonBackReference
    private CategoryDTO category;

    private String categoryName;

    @JsonManagedReference
    private List<ProductDTO> productList;
}
