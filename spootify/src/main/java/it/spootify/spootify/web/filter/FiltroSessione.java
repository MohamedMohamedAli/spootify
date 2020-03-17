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
@Order(1)
public class FiltroSessione implements Filter{
	@Autowired
	private HttpServletRequest http;
	@Autowired
	private UtenteService utenteService;
	private static final String[]OK =  {"login","tabella","/css/","/js/","riproduzione","index"};
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("sono nel filtro sessione");
		String codice = http.getHeader("codice");
		String stato = "";
		String url = http.getRequestURL().toString();
		System.out.println(url+"  whiteList:"+whiteList(url));
		
		if(!whiteList(url)) {
			stato = utenteService.utenteSessione(codice);
			System.out.println("stato: "+stato);
			switch (stato) {
			case DTO.NO:
				System.out.println("non loggato");
				request.setAttribute("dtoAttr", new ErroreDTO("Utente non loggato"));
				request.getRequestDispatcher("/home/errore").forward(request, response);
				return;
			case DTO.SESSIONE_SCADUTA:
				System.out.println("sessione scaduta");
				request.setAttribute("dtoAttr", new ErroreDTO("Sessione scaduta"));
				request.getRequestDispatcher("/home/errore").forward(request, response);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}
	
	public boolean whiteList(String url) {
		for(String s:OK) {
			if(url.contains(s)) {
				return true;
			}
		}
		return false;
	}

}
