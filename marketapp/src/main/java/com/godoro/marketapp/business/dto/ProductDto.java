package com.godoro.marketapp.business.dto;

import com.godoro.marketapp.data.entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ProductDto {
    private long productId;
    private String productName;

    private double salesPrice;

    private Category category;


}
