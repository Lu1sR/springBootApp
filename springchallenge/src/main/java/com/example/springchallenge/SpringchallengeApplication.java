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


	@Scheduled(fixedDelay = 40000, initialDelay = 1000)
	public void scheduleFixedRateWithInitialDelayTask() {

		ObjectMapper objectMapper = new ObjectMapper();
		
		List<LogEntity> httpLogs = new LinkedList<LogEntity>();
		try(
			FileReader reader = new FileReader("./logs/click.json");
			BufferedReader bufferedReader = new BufferedReader(reader);
		)		
		{
			String currentLine; 
			while((currentLine=bufferedReader.readLine()) != null) {
                LogEntity logEntity = objectMapper.readValue(currentLine, LogEntity.class);
				if(logEntity.getXRequestID() != null){
					httpLogs.add(logEntity);
				}                
            }
			System.out.println("#####################################################################################################################"+httpLogs.size());

		} catch (JsonParseException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		try {
			logService.createLogIndexBulk(httpLogs);	
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

}
