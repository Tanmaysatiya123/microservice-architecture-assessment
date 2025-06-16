package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    private OrderService service;
    private OrderController controller;

    @BeforeEach
    public void setup() {
        service = mock(OrderService.class);
        controller = new OrderController(service);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        when(service.createOrder(order)).thenReturn(order);
        ResponseEntity<Order> response = controller.create(order);
        assertEquals(order, response.getBody());
    }

    @Test
    public void testGetOrder() {
        Order order = new Order();
        when(service.getOrder(1L)).thenReturn(order);
        ResponseEntity<Order> response = controller.get(1L);
        assertEquals(order, response.getBody());
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order();
        when(service.updateOrder(1L, order)).thenReturn(order);
        ResponseEntity<Order> response = controller.update(1L, order);
        assertEquals(order, response.getBody());
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(service).deleteOrder(1L);
        ResponseEntity<Void> response = controller.delete(1L);
        assertEquals(204, response.getStatusCodeValue());
    }
}
