package com.godoro.marketapp.data.repository;

import com.godoro.marketapp.data.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {

    List<Product> findProductsByCategory_CategoryId(long categoryId);
    
}
