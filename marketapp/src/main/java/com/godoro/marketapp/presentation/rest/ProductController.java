package com.godoro.marketapp.presentation.rest;

import com.godoro.marketapp.business.dto.ProductDto;
import com.godoro.marketapp.business.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") long productId){
        return new ResponseEntity<>(productService.find(productId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long productId) {

        productService.delete(productId);

    }

}
/*
*   /products/{categoryid}
	/product/{id}
* */