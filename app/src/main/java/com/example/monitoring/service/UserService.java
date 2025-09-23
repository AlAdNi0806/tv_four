package com.example.monitoring.service;

import com.example.monitoring.entity.User;
import com.example.monitoring.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long id) {
        logger.info("Fetching user with id: {}", id);
        return userRepository.findById(id);
    }
    
    public User createUser(User user) {
        logger.info("Creating new user: {}", user.getEmail());
        return userRepository.save(user);
    }
    
    public User updateUser(Long id, User userDetails) {
        logger.info("Updating user with id: {}", id);
        return userRepository.findById(id)
            .map(user -> {
                user.setName(userDetails.getName());
                user.setEmail(userDetails.getEmail());
                return userRepository.save(user);
            }).orElse(null);
    }
    
    public void deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }
}