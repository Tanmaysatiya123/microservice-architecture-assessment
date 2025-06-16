package com.example.orderservice.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        OrderNotFoundException ex = new OrderNotFoundException("Not found");
        assertEquals("Not found", ex.getMessage());
    }
}
