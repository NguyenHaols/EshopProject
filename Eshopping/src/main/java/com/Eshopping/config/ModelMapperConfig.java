package com.Eshopping.config;

import com.Eshopping.DTO.CategoryDTO;
import com.Eshopping.DTO.ProductDTO;
import com.Eshopping.model.Category;
import com.Eshopping.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
