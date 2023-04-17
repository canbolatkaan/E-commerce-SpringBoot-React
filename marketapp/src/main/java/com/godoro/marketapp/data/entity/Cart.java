package com.godoro.marketapp.data.entity;

import com.godoro.marketapp.enums.CartStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @Nullable
    private String customerName;
    @Nullable
    private long cardNumber;
    @Enumerated
    private CartStatus cartStatus = CartStatus.NEW;
}
