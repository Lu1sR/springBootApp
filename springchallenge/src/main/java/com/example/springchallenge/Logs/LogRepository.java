package com.example.springchallenge.Logs;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<LogEntity, String> {
    
}
