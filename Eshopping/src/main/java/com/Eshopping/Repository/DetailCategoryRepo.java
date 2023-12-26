package com.Eshopping.Repository;

import com.Eshopping.model.DetailCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailCategoryRepo extends JpaRepository<DetailCategory,Integer> {
}
