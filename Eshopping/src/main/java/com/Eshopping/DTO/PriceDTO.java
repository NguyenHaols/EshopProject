package com.Eshopping.DTO;

import com.Eshopping.model.Product;
import com.Eshopping.model.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO {
    private int id;
    @JsonBackReference
    private ProductDTO product;
    private SizeDTO size;
    private Long price;
}
