package com.godoro.marketapp.business.service;

import com.godoro.marketapp.business.dto.CartDto;
import com.godoro.marketapp.business.dto.CartProductDto;
import com.godoro.marketapp.business.dto.ProductDto;
import com.godoro.marketapp.data.entity.Cart;

import java.util.List;
/*
/cart/get/{id} (Yoksa yarat)
	/cart/add/{cartid}/{productid}
	/cart/remove/{cartid}/{productid}
	/cart/checkout

 */

public interface CartService {
    void remove(long cartId,long productId);

    CartDto getCart(long cartId);

    void add(long cartId,long productId);

    List<CartProductDto> getProductListByCartId(long cartId);

    CartDto checkout(CartDto cartDto);
}
