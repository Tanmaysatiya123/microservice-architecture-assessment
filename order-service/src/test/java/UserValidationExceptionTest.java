package com.example.orderservice.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserValidationExceptionTest {

    @Test
    public void testExceptionMessage() {
        UserValidationException ex = new UserValidationException("Validation error");
        assertEquals("Validation error", ex.getMessage());
    }
}
