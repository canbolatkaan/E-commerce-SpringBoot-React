package com.godoro.marketapp.business.dto;

import com.godoro.marketapp.data.entity.Cart;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class CartProductDto {
    private long cartProductId;

    private ProductDto product;

    private CartDto cart;
    private int salesQuantity;
}
