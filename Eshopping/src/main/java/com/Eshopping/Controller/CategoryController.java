package com.Eshopping.Controller;

import com.Eshopping.DTO.CategoryDTO;
import com.Eshopping.DTO.DetailCategoryDTO;
import com.Eshopping.DTO.ProductDTO;
import com.Eshopping.Repository.CategoryRepo;
import com.Eshopping.Service.CategoryService;
import com.Eshopping.Service.DetailCategoryService;
import com.Eshopping.Service.ProductService;
import com.Eshopping.payLoad.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DetailCategoryService detailCategoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("getAllCategory")
    public ResponseEntity<?> getAllCategory(){
        ResponseData responseData = new ResponseData();
        List<CategoryDTO> list = categoryService.getAllCategory();
        if(list.size()>=0){
            responseData.setStatus(200);
            responseData.setData(list);
            responseData.setSucces(true);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("getAllDetailCategory")
    public ResponseEntity<?> getAllDetailCategory(){
        ResponseData responseData = new ResponseData();
        List<DetailCategoryDTO> list = detailCategoryService.getAllDetailCategory();
        if(list.size()>=0){
            responseData.setStatus(200);
            responseData.setData(list);
            responseData.setSucces(true);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
