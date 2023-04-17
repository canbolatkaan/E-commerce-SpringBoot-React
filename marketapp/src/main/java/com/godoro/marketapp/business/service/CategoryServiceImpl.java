package com.godoro.marketapp.business.service;

import com.godoro.marketapp.business.dto.CategoryDto;
import com.godoro.marketapp.data.entity.Category;
import com.godoro.marketapp.data.entity.Product;
import com.godoro.marketapp.data.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<CategoryDto> list() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for(Category category : categoryRepository.findAll()){
            categoryDtoList.add(convertToDto(category));
        }
        return categoryDtoList;
    }

    private static CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());



        return categoryDto;
    }
}
