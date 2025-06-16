package com.example.userservice.service;

import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public User getUser(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    public User updateUser(Long id, User user) {
        User existing = getUser(id);
        existing.setUsername(user.getUsername());
        existing.setPassword(user.getPassword());
        existing.setEmail(user.getEmail());
        return repository.save(existing);
    }

    public void deleteUser(Long id) {
        User user = getUser(id);
        repository.delete(user);
    }
}
