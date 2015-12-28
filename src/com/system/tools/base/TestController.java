package com.system.tools.base;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class TestController implements Controller {

	private static final Logger LOGGER = Logger.getLogger(TestController.class);
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String now = (new Date()).toString();
		
		LOGGER.info("Returning hello view with " + now);
	        
	    return new ModelAndView("pages/system/System_attach/System_attach", "now", now);	
		
	}

}

