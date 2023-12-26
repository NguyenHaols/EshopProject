package com.Eshopping.DTO;

import com.Eshopping.model.Order;
import com.Eshopping.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailOrderDTO {
    private int id;
    private Double price;
    private OrderDTO order;
    private ProductDTO product;
}
