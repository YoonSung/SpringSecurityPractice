package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
		
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/")
	public @ResponseBody String home() {
		return "home";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
