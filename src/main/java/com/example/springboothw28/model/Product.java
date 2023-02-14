package com.example.springboothw28.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "The name can not be null or empty")
    private String name;
    @NotNull(message = "The price can not be null")
    private Integer price;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private List<Order> orders;
}
