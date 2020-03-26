package com.wubi.nasathingy.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wubi.nasathingy.beans.Photos;
import com.wubi.nasathingy.services.NasaPhotoService;

@RestController
@RequestMapping("nasathingy")
public class NasaPhotoController {
	Logger logger = LoggerFactory.getLogger(NasaPhotoController.class);
	
	@Autowired
	NasaPhotoService nasaPhotoService;
	
	@GetMapping(value= "photos", produces= { MediaType.APPLICATION_JSON_VALUE })
	public Photos getPhotosForADate(@RequestParam ("earthdate") String earthDate) {
		logger.debug("entering photos with earthDate {}", earthDate);
		Photos photos = nasaPhotoService.getPhotos(earthDate); 
		logger.info("photos are:" + photos);
		return photos;
	}
	
	@GetMapping(value= "image", produces= { MediaType.IMAGE_JPEG_VALUE })
	public @ResponseBody byte[] getPhotoImage(@RequestParam ("img_src") String imgSrc) {
		logger.debug("entering photos with imgSrc {}", imgSrc);
		return nasaPhotoService.getNasaImage(imgSrc);
	}
	
}
