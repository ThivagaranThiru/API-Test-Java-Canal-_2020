package com.quest.canal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
	
	@RequestMapping(value="/testSuccess", method=RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public String testSuccess() {
		return "success";
	}
	
	@RequestMapping(value="/testNotFound", method=RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String testNotFound() {
		return "not found";
	}
	
	@RequestMapping(value="/testError", method=RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public String testError() {
		return "error";
	}

}