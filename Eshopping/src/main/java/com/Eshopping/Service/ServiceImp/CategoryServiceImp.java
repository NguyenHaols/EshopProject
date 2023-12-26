package com.Eshopping.Service.ServiceImp;

import com.Eshopping.DTO.CategoryDTO;
import com.Eshopping.DTO.ProductDTO;
import com.Eshopping.Repository.CategoryRepo;
import com.Eshopping.Service.CategoryService;
import com.Eshopping.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> list = categoryRepo.findAll();
        List<CategoryDTO> categoryDTOList = list.stream()
                                            .map(category -> modelMapper
                                                    .map(category, CategoryDTO.class))
                                                        .collect(Collectors.toList());

        return categoryDTOList;
    };


}
