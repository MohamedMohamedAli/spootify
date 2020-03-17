package it.spootify.spootify.web.rest.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/tabella")
public class HtmlResource {

	@Autowired
	private HttpServletRequest http;
	
	@GetMapping("/index")
	public ModelAndView index() {
		String codice = http.getHeader("codice");
		System.out.println("codice index: "+codice);
		System.out.println("entra in html");
		return new ModelAndView("index.html");
	}
	@GetMapping("/prova")
	public ModelAndView prova() {
		String codice = http.getHeader("codice");
		System.out.println("codice index: "+codice);
		System.out.println("entra in html");
		return new ModelAndView("prova2.html");
	}
}
