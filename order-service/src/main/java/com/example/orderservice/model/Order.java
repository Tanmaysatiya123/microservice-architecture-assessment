package com.example.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String product;
    private Integer quantity;
    private Double price;
}
