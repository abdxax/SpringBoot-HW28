package com.example.springboothw28.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatus {
    private Integer quantity;
    private Integer poductId;
}
