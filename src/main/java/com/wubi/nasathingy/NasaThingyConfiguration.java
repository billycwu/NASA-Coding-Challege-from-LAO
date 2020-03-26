package com.wubi.nasathingy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class NasaThingyConfiguration {

	private String downloadedPicsPath;

	public String getDownloadedPicsPath() {
		return downloadedPicsPath;
	}

	public void setDownloadedPicsPath(String downloadedPicsPath) {
		this.downloadedPicsPath = downloadedPicsPath;
	}
}
