package com.wubi.nasathingy.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping("/nasa-pics")
	public String index() {
		logger.debug("entering nasaphoto");
		return "index";
	}
	
}
