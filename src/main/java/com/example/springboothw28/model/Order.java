package com.example.springboothw28.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    private Integer totalPrice;
    private String dateReceived;
    @Pattern(regexp = "new||inProgress||completed")
    private String status;
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonIgnore
    private MyUser user;
    @ManyToOne
    @JoinColumn(name = "productId",referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}
