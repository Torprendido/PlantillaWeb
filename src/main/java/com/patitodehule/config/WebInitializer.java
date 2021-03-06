package com.patitodehule.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
				SpringWebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "*.do" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

}
