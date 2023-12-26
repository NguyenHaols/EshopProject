package com.Eshopping.Service.ServiceImp;

import com.Eshopping.DTO.DetailCategoryDTO;

import com.Eshopping.Repository.DetailCategoryRepo;
import com.Eshopping.Repository.ProductRepo;
import com.Eshopping.Service.DetailCategoryService;
import com.Eshopping.model.DetailCategory;
import com.Eshopping.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DetailCategoryServiceImp implements DetailCategoryService {

    @Autowired
    private DetailCategoryRepo detailCategoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepo productRepo;


    @Override
    public List<DetailCategoryDTO> getAllDetailCategory() {
        List<DetailCategory> detailCategoryList = detailCategoryRepo.findAll();
        List<DetailCategoryDTO> detailCategoryDTOList = detailCategoryList.stream()
                .map(detailCategory -> modelMapper.map(detailCategory,DetailCategoryDTO.class))
                .collect(Collectors.toList());
        return detailCategoryDTOList;
    }


}
