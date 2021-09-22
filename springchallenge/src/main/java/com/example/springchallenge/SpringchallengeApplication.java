package com.example.springchallenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.example.springchallenge.Logs.LogEntity;
import com.example.springchallenge.Logs.LogService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication
@EnableScheduling
public class SpringchallengeApplication {

	private LogService logService;

	@Autowired
	public SpringchallengeApplication(LogService logService) {
		this.logService = logService;
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringchallengeApplication.class, args);
	}

	
	@Scheduled(cron="0 5 * * * *") // method that executes in the 5th minute of every hour 
	public void scheduleCronjobTask() {
		System.out.println("\nCRONJOB STARTED\n");
		ObjectMapper objectMapper = new ObjectMapper();		
		List<LogEntity> httpLogs = new LinkedList<LogEntity>();


		try(
			FileReader reader = new FileReader("./logs/click.json");
			BufferedReader bufferedReader = new BufferedReader(reader);
		)		
		{
			String currentLine; 
			while((currentLine=bufferedReader.readLine()) != null) {
                LogEntity logEntity = objectMapper.readValue(currentLine, LogEntity.class); // json to object
				if(logEntity.getXRequestID() != null){
					httpLogs.add(logEntity);
				}                
            }
			
		} catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		try {
			logService.createLogIndexBulk(httpLogs);	// saveAll elascticsearch repository implementation
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

}
