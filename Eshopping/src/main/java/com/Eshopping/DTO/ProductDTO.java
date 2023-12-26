package com.Eshopping.DTO;

import com.Eshopping.model.*;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    private int id;
    private String title;
    private String description;
//    @JsonManagedReference
//    private CategoryDTO category;
//    private List<PromoDTO> promoList;
//    private List<RatingStarDTO> ratingStarList;
//    private List<DetailOrderDTO> detailOrderList;
    @JsonManagedReference
    private List<PriceDTO> priceList;

    @JsonBackReference
    private DetailCategoryDTO detailCategory;

}
