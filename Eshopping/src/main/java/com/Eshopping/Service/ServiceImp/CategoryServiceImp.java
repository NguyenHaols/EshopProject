package com.Eshopping.Service.ServiceImp;

import com.Eshopping.Repository.CategoryRepo;
import com.Eshopping.Service.CategoryService;
import com.Eshopping.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategory() {
        List<Category> list = categoryRepo.findAll();


        return null;
    };
}
