package com.qa.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// localhost:8080/
@RestController // indicates to Spring that this is a bean.
@RequestMapping("/basic")
public class BasicController {

	// localhost:8080/basic
	// @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD}) (Same as @GetMapping)
	@GetMapping("/something")
	public String basicHTMLResponse() {
		return "<h1>Hello World</h1>";
	}
	
	@GetMapping()
	public String basicHTMLResponse1() {
		return "<h1>Yes Sir</h1>";
	}
}