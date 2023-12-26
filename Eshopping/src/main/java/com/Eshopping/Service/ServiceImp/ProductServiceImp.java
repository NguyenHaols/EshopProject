package com.Eshopping.Service.ServiceImp;

import com.Eshopping.DTO.ProductDTO;
import com.Eshopping.Repository.ProductRepo;
import com.Eshopping.Service.ProductService;
import com.Eshopping.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> productList = productRepo.findAll();
        List<ProductDTO> productDTOList = productList.stream()
                                            .map(product -> modelMapper.map(product, ProductDTO.class))
                                            .collect(Collectors.toList());
        return productDTOList;
    }

    @Override
    public ProductDTO getProductById(int id) {
        Product product = productRepo.findById(id).orElse(null);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getProductsByDetailCategoryId(int detailCategoryId) {
        List<Product> productList = productRepo.findProductBydetailCategoryId(detailCategoryId);
        List<ProductDTO> productDTOList = productList.stream()
                                            .map(product -> modelMapper.map(product,ProductDTO.class))
                                            .collect(Collectors.toList());
        return productDTOList;
    }
}
