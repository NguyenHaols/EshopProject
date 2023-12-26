package com.Eshopping.Service;

import com.Eshopping.DTO.CategoryDTO;
import com.Eshopping.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    public List<CategoryDTO> getAllCategory();
}
