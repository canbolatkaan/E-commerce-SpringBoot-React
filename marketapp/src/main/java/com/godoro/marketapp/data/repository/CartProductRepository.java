package com.godoro.marketapp.data.repository;

import com.godoro.marketapp.data.entity.Cart;
import com.godoro.marketapp.data.entity.CartProduct;
import com.godoro.marketapp.data.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartProductRepository extends CrudRepository<CartProduct,Long> {

    CartProduct findCartProductByCart_CartIdAndProduct_ProductId(long cart_cartId,long product_productId);
    List<CartProduct> findCartProductsByCart_CartId(long cart_cartId);

}
