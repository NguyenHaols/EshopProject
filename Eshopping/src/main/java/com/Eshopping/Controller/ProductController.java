package com.Eshopping.Controller;

import com.Eshopping.DTO.PriceDTO;
import com.Eshopping.DTO.ProductDTO;
import com.Eshopping.Service.PriceService;
import com.Eshopping.Service.ProductService;
import com.Eshopping.payLoad.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private PriceService priceService;

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct(){
        ResponseData responseData = new ResponseData();
        List<ProductDTO> productDTO = productService.getAllProduct();
        if(productDTO.size() >= 0){
            responseData.setData(productDTO);
            responseData.setSucces(true);
        }

        return new ResponseEntity<>(responseData,HttpStatus.OK);
    };

    @PostMapping("/getProductById")
    public ResponseEntity<?> getProductById(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        ProductDTO productDTO = productService.getProductById(id);
        List<PriceDTO> priceDTOList = priceService.getPricesByProductId(id);
        productDTO.setPriceList(priceDTOList);
        if(productDTO != null){
            responseData.setSucces(true);
            responseData.setData(productDTO);
        }
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    };

    @PostMapping("/getProductByDetailCategoryId")
    public ResponseEntity<?> getProductByDetailCategoryId(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        List<ProductDTO> productDTOList = productService.getProductsByDetailCategoryId(id);
        if(productDTOList.size() >= 0){
            responseData.setData(productDTOList);
            responseData.setSucces(true);
        }
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    };
}
