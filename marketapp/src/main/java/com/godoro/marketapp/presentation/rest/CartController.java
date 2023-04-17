package com.godoro.marketapp.presentation.rest;
/*
/cart/get/{id} (Yoksa yarat)
	/cart/add/{cartid}/{productid}
	/cart/remove/{cartid}/{productid}
	/cart/checkout

 */

import com.godoro.marketapp.business.dto.CartDto;
import com.godoro.marketapp.business.dto.CartProductDto;
import com.godoro.marketapp.business.dto.ProductDto;
import com.godoro.marketapp.business.service.CartService;
import com.godoro.marketapp.business.service.ProductService;
import com.godoro.marketapp.data.entity.CartProduct;
import com.godoro.marketapp.data.repository.CartProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    private CartProductRepository cartProductRepository;

    public CartController(CartService cartService,CartProductRepository cartProductRepository){
        this.cartService = cartService;
        this.cartProductRepository = cartProductRepository;
    }

    @GetMapping("get/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable("id") long cartId){
        return new ResponseEntity<>(cartService.getCart(cartId), HttpStatus.OK);
    }
    @GetMapping("get/cardProducts/{id}")
    public ResponseEntity<List<CartProductDto>> getCartProductList(@PathVariable("id") long cartId){
        return new ResponseEntity<>(cartService.getProductListByCartId(cartId), HttpStatus.OK);
    }
    @PostMapping("add/{cartId}/{productId}")
    public ResponseEntity<?> addProduct(@PathVariable("cartId") long cartId,@PathVariable("productId") long productId){
        Map<String, Object> map = new LinkedHashMap<>();
        cartService.add(cartId,productId);
        map.put("status", 1);
        map.put("message", "Record is Saved Successfully!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @GetMapping("remove/{cartId}/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable("cartId") long cartId,@PathVariable("productId") long productId){
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            cartService.remove(cartId,productId);
            map.put("status", 1);
            map.put("message", "Record is deleted successfully!");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("checkout")
    public ResponseEntity<?> checkout(@RequestBody() CartDto cartDto){
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            CartDto cartDto1 = cartService.checkout(cartDto);
            map.put("status", 1);
            map.put("data", cartDto1);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }



}
