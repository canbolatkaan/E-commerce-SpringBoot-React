package com.godoro.marketapp.business.service;

import com.godoro.marketapp.business.dto.ProductDto;
import com.godoro.marketapp.data.entity.Product;
import com.godoro.marketapp.data.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public void delete(long productId) {
        productRepository.deleteById(productId);

    }

    @Override
    public ProductDto find(long productId) {
        Optional<Product> optional = productRepository.findById(productId
        );
        if(optional.isPresent()){
            return convertToDto(optional.get());
        }
        return null;
    }

    @Override
    public List<ProductDto> listByCategoryId(long categoryId) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product : productRepository.findProductsByCategory_CategoryId(categoryId)){
            productDtoList.add(convertToDto(product));
        }
        return productDtoList;

    }


    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setSalesPrice(product.getSalesPrice());
        productDto.setCategory(product.getCategory());


        return productDto;
    }


}
