package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return ResponseEntity.ok(service.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOrder(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order) {
        return ResponseEntity.ok(service.updateOrder(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
