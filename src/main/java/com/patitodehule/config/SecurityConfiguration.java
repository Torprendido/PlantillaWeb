package com.patitodehule.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	@Autowired
	DataSource dataSource;
     
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication().dataSource(dataSource)
    			.usersByUsernameQuery(
    					"select NOMBRE as 'username', PASSWORD as 'password', ACTIVO as 'enabled' " 
    							+ "from USUARIO "
    							+ "where NOMBRE = ? and ACTIVO = 1 ")
    			.authoritiesByUsernameQuery(
    					"select u.NOMBRE as 'username', ur.ROL as 'role ' "
    							+ "from USUARIO_ROL ur "
    							+ "inner join USUARIO u on ur.ID_USUARIO = u.ID_USUARIO "
    							+ "where u.NOMBRE = ? ");
    }
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
				.antMatchers("/home/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
				.antMatchers("/administracionUsuarios/**").access("hasRole('ROLE_ADMIN')")
				.and()
				.formLogin()
						.loginPage("/login.do")
						.loginProcessingUrl("/validaUsuario")
						.defaultSuccessUrl("/home/welcome.do")
						.failureUrl("http://www.google.com")
				.usernameParameter("username").passwordParameter("password")
				.and()
					.csrf();;
    }
}
