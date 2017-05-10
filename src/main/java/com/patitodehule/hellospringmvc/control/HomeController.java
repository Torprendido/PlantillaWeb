package com.patitodehule.hellospringmvc.control;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	public HomeController() {
	}
	
	@RequestMapping(value = "welcome.do")
	public String home(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("usuario", username);;
		return "/home/welcome";
	}
	
}
