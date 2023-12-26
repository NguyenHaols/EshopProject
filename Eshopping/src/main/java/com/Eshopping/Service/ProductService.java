package com.Eshopping.Service;

import com.Eshopping.DTO.ProductDTO;
import com.Eshopping.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    public List<ProductDTO> getAllProduct();
    public ProductDTO getProductById(int id);
    public List<ProductDTO> getProductsByDetailCategoryId(int detailCategoryId);
}
