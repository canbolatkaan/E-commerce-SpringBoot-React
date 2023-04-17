package com.godoro.marketapp.presentation.rest;

import com.godoro.marketapp.business.dto.ProductDto;
import com.godoro.marketapp.business.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private ProductService productService;

    public ProductsController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable("categoryId") long categoryId){
        return new ResponseEntity<>(productService.listByCategoryId(categoryId), HttpStatus.OK);
    }



}