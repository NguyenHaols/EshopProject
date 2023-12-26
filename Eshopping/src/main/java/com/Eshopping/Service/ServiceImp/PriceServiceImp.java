package com.Eshopping.Service.ServiceImp;

import com.Eshopping.DTO.PriceDTO;
import com.Eshopping.DTO.SizeDTO;
import com.Eshopping.Repository.PriceRepo;
import com.Eshopping.Repository.ProductRepo;

import com.Eshopping.Service.PriceService;

import com.Eshopping.model.Price;
import com.Eshopping.model.Size;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImp implements PriceService {

    @Autowired
    private PriceRepo priceRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PriceDTO> getPricesByProductId(int id) {
        List<Price> priceList = priceRepo.getPricesByProductId(id);
        List<PriceDTO> priceDTOList = priceList.stream()
                                        .map(price -> modelMapper.map(price, PriceDTO.class))
                                            .collect(Collectors.toList());
        return priceDTOList;
    }

    @Override
    public List<PriceDTO> findAll() {
        List<Price> priceList = priceRepo.findAll();
        List<PriceDTO> priceDTOList = priceList.stream()
                .map(price -> modelMapper.map(price, PriceDTO.class))
                .collect(Collectors.toList());
        return priceDTOList;
    }
}
