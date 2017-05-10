package com.patitodehule.plantillaweb.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {
	
	public Login() {
		
	}
	
	/**
	 * Este metodo solo abre la ventana de login, el control de la session
	 * se encuentra en el archivo de configuracion spring-securiry.xml
	 * 
	 * 
	 * @return la el jsp que despliega el formulario del login
	 */
	@RequestMapping(value="/login.do")
	public String login() {
		return "/login";
	}
}
