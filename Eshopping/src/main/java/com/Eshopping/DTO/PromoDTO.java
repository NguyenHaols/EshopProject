package com.Eshopping.DTO;

import com.Eshopping.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoDTO {
    private int id;
    private int percent;
    private Date dayStart;
    private Date dayEnd;
    private ProductDTO product;
}
