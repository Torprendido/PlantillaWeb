package com.patitodehule.plantillaweb.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

@Component
public class WebSessionListener implements ApplicationListener<ApplicationEvent> {

	private int counter = 0;
	private SimpleDateFormat sdf;
	private HttpSessionCreatedEvent hsce;
	
	public WebSessionListener() {
		System.out.println("[listener creado]");
		sdf = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	/**
	 * Metodo que reacciona a los eventos de la session,
	 * para implementarlo con spring se agrega el listener-class HttpSessionEventPublisher
	 * en un nuevo listener en el web xml. Tambien es indispensabe el filtro: springSecurityFilterChain
	 * (ver en web,xml)
	 * 
	 * 
	 */
	@Override
    public void onApplicationEvent(ApplicationEvent event) {
		Date timestamp = new Date(event.getTimestamp());
//		if (event instanceof HttpSessionCreatedEvent) {
//			hsce = (HttpSessionCreatedEvent) event;
//        } else if (event instanceof HttpSessionDestroyedEvent) {
//        	counter --;
//        } else if (event instanceof InteractiveAuthenticationSuccessEvent) {
//			counter += 2; //Antes de que se logea un usuario ocurre HttpSessionDestroyedEvent
//			System.out.println("Total sessiones creadas " + counter);
//			System.out.println("Session creada en "
//					+ sdf.format(timestamp));
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			System.out.println("username: " + auth.getName());
//			hsce.getSession().setAttribute("username", auth.getName());
//			
//			hsce.getSession().setMaxInactiveInterval(60/*1 minuto*/);
//			
//			hsce = null;
//		}
    }
	
}
