package com.innovator.practice.learing.portal.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associate")
public class AssociateController {
	
	@GetMapping("/courses")
	//@PreAuthorize("hasRole('ASSOCIATE')")
	private String getCourses() {
		return "List of courses";
	}

}
