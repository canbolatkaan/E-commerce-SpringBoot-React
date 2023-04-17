package com.godoro.marketapp.business.service;

import com.godoro.marketapp.business.dto.ProductDto;

import java.util.List;

public interface ProductService {

    void delete(long productId);
    ProductDto find(long accountId);

    List<ProductDto> listByCategoryId(long categoryId);
}
