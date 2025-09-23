package com.example.monitoring.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class GraylogService {
    
    public byte[] exportLogsToCsv() throws IOException {
        List<LogEntry> logs = fetchLogsFromGraylog();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintWriter writer = new PrintWriter(baos);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                 .withHeader("Timestamp", "Level", "Message", "Logger", "Thread"))) {
            
            for (LogEntry log : logs) {
                csvPrinter.printRecord(
                    log.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    log.getLevel(),
                    log.getMessage(),
                    log.getLogger(),
                    log.getThread()
                );
            }
            
            csvPrinter.flush();
        }
        
        return baos.toByteArray();
    }
    
    private List<LogEntry> fetchLogsFromGraylog() {
        // Simulated log data (в реальном проекте здесь был бы запрос к Graylog API)
        List<LogEntry> logs = new ArrayList<>();
        
        logs.add(new LogEntry(LocalDateTime.now().minusHours(1), "INFO", 
            "User created successfully", "UserService", "http-nio-8090-exec-1"));
        logs.add(new LogEntry(LocalDateTime.now().minusMinutes(30), "INFO", 
            "Order processed", "OrderService", "http-nio-8090-exec-2"));
        logs.add(new LogEntry(LocalDateTime.now().minusMinutes(15), "INFO", 
            "Product updated", "ProductService", "http-nio-8090-exec-3"));
        logs.add(new LogEntry(LocalDateTime.now().minusMinutes(5), "WARN", 
            "Database connection slow", "DatabaseConfig", "http-nio-8090-exec-1"));
        
        return logs;
    }
    
    public static class LogEntry {
        private LocalDateTime timestamp;
        private String level;
        private String message;
        private String logger;
        private String thread;
        
        public LogEntry(LocalDateTime timestamp, String level, String message, String logger, String thread) {
            this.timestamp = timestamp;
            this.level = level;
            this.message = message;
            this.logger = logger;
            this.thread = thread;
        }
        
        // Getters
        public LocalDateTime getTimestamp() { return timestamp; }
        public String getLevel() { return level; }
        public String getMessage() { return message; }
        public String getLogger() { return logger; }
        public String getThread() { return thread; }
    }
}