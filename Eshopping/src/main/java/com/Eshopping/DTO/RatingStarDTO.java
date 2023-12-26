package com.Eshopping.DTO;

import com.Eshopping.model.Product;
import com.Eshopping.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingStarDTO {
    private int id;
    private String feedback;
    private int ratePoint;
    private UserDTO user;
    private ProductDTO product;
}
