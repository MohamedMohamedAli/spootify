package it.spootify.spootify.web.filter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.spootify.spootify.web.filter.FiltroAdmin;

@Configuration
public class ConfigFiltro {
	
	
	@Bean
	public FilterRegistrationBean<FiltroAdmin> registrationBeanAdmin(){
		FilterRegistrationBean<FiltroAdmin> registrationBeanAdmin = new FilterRegistrationBean<>();
		
		registrationBeanAdmin.setFilter(new FiltroAdmin());
		registrationBeanAdmin.addUrlPatterns("/utente/admin/*");
		
		return registrationBeanAdmin;
	}

}
