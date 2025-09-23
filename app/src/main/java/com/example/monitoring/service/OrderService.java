package com.example.monitoring.service;

import com.example.monitoring.entity.Order;
import com.example.monitoring.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    
    @Autowired
    private OrderRepository orderRepository;
    
    public List<Order> getAllOrders() {
        logger.info("Fetching all orders");
        return orderRepository.findAll();
    }
    
    public Optional<Order> getOrderById(Long id) {
        logger.info("Fetching order with id: {}", id);
        return orderRepository.findById(id);
    }
    
    public Order createOrder(Order order) {
        logger.info("Creating new order for user: {}", order.getUser().getId());
        return orderRepository.save(order);
    }
    
    public Order updateOrder(Long id, Order orderDetails) {
        logger.info("Updating order with id: {}", id);
        return orderRepository.findById(id)
            .map(order -> {
                order.setTotalAmount(orderDetails.getTotalAmount());
                return orderRepository.save(order);
            }).orElse(null);
    }
    
    public void deleteOrder(Long id) {
        logger.info("Deleting order with id: {}", id);
        orderRepository.deleteById(id);
    }
}