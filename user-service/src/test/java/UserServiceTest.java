package com.example.userservice.service;

import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleUser = new User(1L, "john", "pass123", "john@example.com");
    }

    @Test
    void testSave() {
        when(userRepository.save(sampleUser)).thenReturn(sampleUser);
        User saved = userService.createUser(sampleUser);
        assertEquals("john", saved.getUsername());
    }

    @Test
    void testGet_ExistingUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        User found = userService.getUser(1L);
        assertEquals("john", found.getUsername());
    }

    @Test
    void testGet_NotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUser(2L));
    }

    @Test
    void testUpdate() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User update = new User(null, "updated", "newpass", "updated@example.com");
        User result = userService.updateUser(1L, update);
        assertEquals("updated", result.getUsername());
    }

    @Test
    void testDelete() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        doNothing().when(userRepository).delete(sampleUser); // ✅ fix here

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(sampleUser); // ✅ verify correct method
    }
}
