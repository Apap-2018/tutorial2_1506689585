package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	
	@RequestMapping("/viral")
	public String index() {
		return "viral";
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value = "name") String name, Model model) {
		model.addAttribute("name",name);
		return "challenge";
	}
	
	@RequestMapping(value= {"/challenge","challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		} else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}
	
	@RequestMapping("/generator")
	public String viralGenerator(
			@RequestParam(value = "a", required = false, defaultValue = "0") Integer number1, 
			@RequestParam(value = "b", required = false, defaultValue = "0") Integer number2, 
			Model model) {
		
		String temp = "hm";
		if (number1 > 1) {
			for (int i=1;i<number1;i++) {
				temp += "m";
			}
		}
		
		String words = temp;
		if (number2 > 1) {
			for (int j=1;j<number2;j++) {
				words += " "  + temp;
			}
		}

		model.addAttribute("word", words);
		model.addAttribute("a", number1);
		model.addAttribute("b", number2);
		
		return "generator";
	}
}
