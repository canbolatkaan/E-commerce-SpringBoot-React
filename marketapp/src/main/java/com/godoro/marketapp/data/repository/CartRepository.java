package com.godoro.marketapp.data.repository;

import com.godoro.marketapp.data.entity.Cart;
import com.godoro.marketapp.data.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository  extends CrudRepository<Cart,Long> {
}
