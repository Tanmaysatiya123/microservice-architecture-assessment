package com.example.orderservice.service;

import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.exception.UserValidationException;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final WebClient webClient;

    @Value("${userservice.url}")
    private String userServiceUrl;

    public OrderService(OrderRepository repository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.webClient = webClientBuilder.build();
    }

    public Order createOrder(Order order) {
        validateUser(order.getUserId());
        return repository.save(order);
    }

    public Order getOrder(Long id) {
        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
    }

    public Order updateOrder(Long id, Order order) {
        Order existing = getOrder(id);
        existing.setUserId(order.getUserId());
        existing.setProduct(order.getProduct());
        existing.setQuantity(order.getQuantity());
        existing.setPrice(order.getPrice());
        return repository.save(existing);
    }

    public void deleteOrder(Long id) {
        repository.delete(getOrder(id));
    }

    private void validateUser(Long userId) {
        try {
            webClient.get()
                     .uri(userServiceUrl + "/" + userId)
                     .retrieve()
                     .bodyToMono(String.class)
                     .block();
        } catch (Exception e) {
            throw new UserValidationException("User not found for id " + userId);
        }
    }
}
