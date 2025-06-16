package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User sampleUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        sampleUser = new User(1L, "john", "pass", "john@example.com");
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(sampleUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sampleUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john"));
    }

    @Test
    void testGetUser() throws Exception {
        when(userService.getUser(1L)).thenReturn(sampleUser);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(sampleUser);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sampleUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
