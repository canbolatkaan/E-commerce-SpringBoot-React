package com.godoro.marketapp.business.dto;

import com.godoro.marketapp.data.entity.CartProduct;
import com.godoro.marketapp.enums.CartStatus;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
public class CartDto {
    private long cartId;
    private String customerName;
    private long cardNumber;
    private CartStatus cartStatus = CartStatus.NEW;

}