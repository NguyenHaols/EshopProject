package com.Eshopping.Service;

import com.Eshopping.DTO.PriceDTO;
import com.Eshopping.DTO.SizeDTO;

import java.util.List;

public interface PriceService {
    public List<PriceDTO> getPricesByProductId(int id);
    public List<PriceDTO> findAll();
}
