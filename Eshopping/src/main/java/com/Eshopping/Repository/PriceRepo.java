package com.Eshopping.Repository;

import com.Eshopping.model.Price;
import com.Eshopping.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<Price,Integer> {

    List<Price> getPricesByProductId(int id);
}
