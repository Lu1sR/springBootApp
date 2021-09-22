package com.example.springchallenge.Logs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private LogRepository productRepository;

    @Autowired
    public LogService(LogRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createLogIndexBulk(final List<LogEntity> logs) {
        productRepository.saveAll(logs);
    }

    public void createLogIndex(final LogEntity log) {
        productRepository.save(log);
    }
    
}
