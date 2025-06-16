package com.example.orderservice.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {

    @Test
    public void testHandleOrderNotFound() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        OrderNotFoundException ex = new OrderNotFoundException("Order not found");
        ResponseEntity<String> response = handler.handleOrderNotFound(ex);
        assertEquals("Order not found", response.getBody());
        assertEquals(404, response.getStatusCodeValue());
    }

}
