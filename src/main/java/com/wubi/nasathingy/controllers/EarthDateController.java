package com.wubi.nasathingy.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wubi.nasathingy.beans.RequiredEarthDates;

@RestController
public class EarthDateController {
	Logger logger = LoggerFactory.getLogger(EarthDateController.class);
	private static final String REQUIRED_EARTH_DATE_FILE = "/requiredEarthDates.txt";

	RequiredEarthDates requiredEarthDates = new RequiredEarthDates();
	
	@GetMapping(value= "requiredearthdates", produces= { MediaType.APPLICATION_JSON_VALUE })
	public RequiredEarthDates getRequiredEarthDates() {
		logger.debug("entering getRequiredEarthDates");
		
		return requiredEarthDates;
	}
	
	@PostConstruct
	public void initializeRequiredDates() {
		logger.debug("entering requiredEarthDates...");
		
		try (InputStream is = getClass().getResourceAsStream(REQUIRED_EARTH_DATE_FILE);
				Stream<String> datesFromFile = 
						new BufferedReader(new InputStreamReader(is)).lines()) {
			datesFromFile.forEach(origDate -> {
				logger.debug("origDate is:{}", origDate);
				
				// now parsing dates with different date format patterns
			    final List<String> dateFormats = Arrays.asList("MM/dd/yy", "MMM d, yyyy", "MMM-d-yyyy");    
			    SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
			    
		        Date date = null;
			    for(String format: dateFormats){
			        SimpleDateFormat sdf = new SimpleDateFormat(format);
			        try{
			        	date = sdf.parse(origDate);
			        	break;
			        } catch (ParseException e) {
			             //intentionally empty
			        }
			    }
			    if (date != null) {
			    	requiredEarthDates.addRequiredEarthDate(newFormat.format(date));
			    } else {
			    	logger.error("This date is unexpected:  {}", origDate);
			    }
			});
		} catch (IOException e) {
			logger.error("Unable to read requiredEarthDates.txt.", e);
		}
	}

}
