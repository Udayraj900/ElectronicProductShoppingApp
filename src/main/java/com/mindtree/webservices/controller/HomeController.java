package com.mindtree.webservices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/seller")
	public String sellerPage() {
		return "sellerpage";
	}
	
	@RequestMapping("/buyer")
	public String buyerPage() {
		return "buyerpage";
	}
}
