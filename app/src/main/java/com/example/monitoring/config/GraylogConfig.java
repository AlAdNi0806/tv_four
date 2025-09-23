package com.example.monitoring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class GraylogConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(GraylogConfig.class);
    
    @PostConstruct
    public void init() {
        logger.info("Graylog configuration initialized");
        logger.info("GELF UDP appender configured for Graylog");
    }
}