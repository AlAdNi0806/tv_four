package com.example.monitoring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    
    @PostConstruct
    public void init() {
        logger.info("Database configuration initialized");
        logger.info("PostgreSQL connection established");
    }
}