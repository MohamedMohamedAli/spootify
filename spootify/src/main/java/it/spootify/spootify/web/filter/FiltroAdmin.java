package it.spootify.spootify.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import it.spootify.spootify.service.UtenteService;
import it.spootify.spootify.web.dto.DTO;
import it.spootify.spootify.web.dto.ErroreDTO;

@Component
@Order(2)
public class FiltroAdmin implements Filter{
	
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private HttpServletRequest http;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("sono nel filtro admin");
		String codice = http.getHeader("codice");
		System.out.println(codice);
		String url = http.getRequestURL().toString();
		System.out.println("url in admin: "+url);
		
		if(url.contains("admin")) {
			String stato = utenteService.utenteAdmin(codice);
			if(stato.equals(DTO.NO)) {
				request.setAttribute("dtoAttr", new ErroreDTO("Area riservata"));
				request.getRequestDispatcher("/home/errore").forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
	}

}
