package com.otis.webapp;

import java.text.DateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getJson() {
		final Map<String, Object> dataBag = new HashMap<>();
		dataBag.put("cartID", Math.random());
		dataBag.put("lineItems", Arrays.asList("item1", "item2"));

		return new ResponseEntity<Object>(dataBag, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> getData(HttpServletRequest request) {
		logger.info(request.getHeader("Accept"));
		
		return new ResponseEntity<Object>(Arrays.asList("a", "b", "c"), HttpStatus.OK);
	}
	
}
