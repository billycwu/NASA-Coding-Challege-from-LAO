package com.wubi.nasathingy.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.wubi.nasathingy.NasaThingyConfiguration;
import com.wubi.nasathingy.beans.Photos;

@Component
public class NasaPhotoService {
	Logger logger = LoggerFactory.getLogger(NasaPhotoService.class);

	private static final String NASA_PHOTO_REST_URI = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";
	private static final String API_KEY = "api_key";
	private static final String API_KEY_VALUE = "0hBTwS2MKxqdRKurnPxJ9BVOf0l1EwDpsDgaqpx6";
	private static final String EARTH_DATE = "earth_date";

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	NasaThingyConfiguration config;

	/**
	 * Call NASA API to get Photos in JSON
	 * @param earthDate
	 * @return
	 */
	public Photos getPhotos(String earthDate) {
		logger.debug("entering with earthDate {}", earthDate);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(NASA_PHOTO_REST_URI)
				.queryParam(API_KEY, API_KEY_VALUE).queryParam(EARTH_DATE, earthDate);

		logger.debug("uri is: {}", builder.toUriString());
		return restTemplate.getForObject(builder.toUriString(), Photos.class);
	}

	/**
	 * This method checks to see if we have the image stored locally.  If so, use it, else go 
	 * to NASA
	 * @param imageUrl
	 * @return
	 */
	public byte[] getNasaImage(String imageUrl) {
		logger.debug("entering with imageUrl {}", imageUrl);
		byte[] image;
		Path filePath = Paths.get(config.getDownloadedPicsPath(), transformUrlToFilePath(imageUrl));
		// if file exists locally, get it
		if (Files.exists(filePath)) {
			logger.info("Retrieving image locally: {}", filePath);
			try {
				BufferedImage bImage = ImageIO.read(new File("sample.jpg"));
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ImageIO.write(bImage, "jpg", bos);
				image = bos.toByteArray();
			} catch (IOException e) {
				logger.warn("Unable to retrieve the image file {}.  Getting it from NASA.", e);
				image = fetchAndStore(imageUrl).getBody();
			}
		} else {
			image = fetchAndStore(imageUrl).getBody();
		}
		return image;
	}

	private ResponseEntity<byte[]> fetchAndStore(String imageUrl) {
		ResponseEntity<byte[]> imageBytesResponse = restTemplate.getForEntity(imageUrl, byte[].class);
		// we need to redirect if the image has been moved
		if (imageBytesResponse.getStatusCode().equals(HttpStatus.MOVED_PERMANENTLY)) {
			URI location = imageBytesResponse.getHeaders().getLocation();
			if (location != null) {
				logger.info("Redirecting... the new location is: {}", location);
				imageBytesResponse = restTemplate.getForEntity(location, byte[].class);
			} else {
				throw new RuntimeException("Unable to fetch image from NASA");
			}
		}

		// now we persist the file
		try {
			Path storagePath = Paths.get(config.getDownloadedPicsPath());
			if (!Files.exists(storagePath)) {
				Files.createDirectories(storagePath);
			}
			Path filePath = Paths.get(config.getDownloadedPicsPath(), transformUrlToFilePath(imageUrl));
			logger.debug("filePath is:{}", filePath.toString());

			Files.write(filePath, imageBytesResponse.getBody());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Unable to persist file.", e);
		}
		return imageBytesResponse;
	}

	private String transformUrlToFilePath(String imageUrl) {
		String imageFileName = imageUrl.replaceAll(":", "-");
		imageFileName = imageFileName.replaceAll("/", "-");
		return imageFileName;
	}

}
