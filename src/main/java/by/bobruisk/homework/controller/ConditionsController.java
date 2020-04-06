package by.bobruisk.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConditionsController {

	@GetMapping( "/conditions")
	public String conditionsPage() {
		
		return "conditions";
	}
}
