package com.godoro.marketapp.data.repository;

import com.godoro.marketapp.data.entity.Category;
import com.godoro.marketapp.data.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository  extends CrudRepository<Category,Long> {
}
