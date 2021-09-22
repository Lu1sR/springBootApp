package com.example.springchallenge.Logs;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends ElasticsearchRepository<LogEntity, String> {
    
}
