package com.godoro.marketapp.presentation.rest;


import com.godoro.marketapp.business.dto.CategoryDto;
import com.godoro.marketapp.business.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController (CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return new ResponseEntity<>(categoryService.list(), HttpStatus.OK);
    }
}
