package com.wubi.nasathingy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.wubi.nasathingy.beans.RequiredEarthDates;
import com.wubi.nasathingy.controllers.NasaPhotoController;

@SpringBootApplication
public class NasaThingyApplication {
	Logger logger = LoggerFactory.getLogger(NasaThingyApplication.class);
	private static final String REQUIRED_EARTH_DATE_FILE = "/requiredEarthDates.txt";

	public static void main(String[] args) {
		SpringApplication.run(NasaThingyApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
	    return new ByteArrayHttpMessageConverter();
	}
	
	@Bean
	@Scope("singleton")
	public RequiredEarthDates requiredEarthDates() {
		logger.debug("entering requiredEarthDates...");
		RequiredEarthDates requiredEarthDates = new RequiredEarthDates();
		
		try (InputStream is = getClass().getResourceAsStream(REQUIRED_EARTH_DATE_FILE);
				Stream<String> datesFromFile = 
						new BufferedReader(new InputStreamReader(is)).lines()) {
			datesFromFile.forEach(date -> {
				logger.debug("a line is:{}", date);
				requiredEarthDates.addRequiredEarthDate(date);
			});
		} catch (IOException e) {
			// oops, should probably hardcode the values here
			e.printStackTrace();
		}		
		return requiredEarthDates;
	}
}
