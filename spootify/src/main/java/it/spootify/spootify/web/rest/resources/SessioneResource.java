package it.spootify.spootify.web.rest.resources;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.spootify.spootify.service.UtenteService;
import it.spootify.spootify.web.dto.ConfermaDTO;
import it.spootify.spootify.web.dto.DTO;
import it.spootify.spootify.web.dto.ErroreDTO;
import it.spootify.spootify.web.dto.UtenteDTO;

@RestController
@RequestMapping(value = "/home")
public class SessioneResource {
	
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private HttpServletRequest httpServletRequest;
	

	
	@PostMapping("/login")
	public ResponseEntity<DTO> login(@RequestBody UtenteDTO utenteDTO){
		String stato = utenteService.eseguiAcesso(utenteDTO);
		switch (stato) {
		case DTO.NO:
			return ResponseEntity.status(401).body(new ErroreDTO("Credenziali errate"));
		case DTO.CREATO:
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroreDTO("In attesa di conferma"));
		case DTO.DISABILITATO:
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroreDTO("L'account è stato disabilitato"));
		}
		return ResponseEntity.ok(new ConfermaDTO("Accesso eseguito con successo. Codice ="+stato+""));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<DTO> logout(){
		String codice = httpServletRequest.getHeader("codice");
		System.out.println("codice: "+codice);
		String stato = utenteService.logout(codice);
		if(stato.equals(DTO.NO)) {
			return ResponseEntity.status(401).body(new ErroreDTO("L'utente non è loggato"));
		}
		return ResponseEntity.ok(new ConfermaDTO("Logout eseguito con successo"));
	}
	
	@GetMapping("/sessione")
	public ResponseEntity<DTO> sessione(){
		String codice = httpServletRequest.getHeader("codice");
		System.out.println("codice: "+codice);
		String stato = utenteService.utenteSessione(codice);
		switch (stato) {
		case DTO.NO:
			return ResponseEntity.status(401).body(new ErroreDTO("L'utente non è loggato"));
		case DTO.SESSIONE_SCADUTA:
			return ResponseEntity.status(401).body(new ErroreDTO("Sessione scaduta"));
		}
		return ResponseEntity.ok(new ConfermaDTO("Sei in sessione"));
	}
	
	@GetMapping("/errore")
	public ResponseEntity<DTO> errore(ServletRequest request){
		System.out.println("entro nel get");
		ErroreDTO errore = (ErroreDTO)request.getAttribute("dtoAttr");
		return ResponseEntity.status(401).body(errore);
	}
	
	@PutMapping("/errore")
	public ResponseEntity<DTO> errorePut(ServletRequest request){
		System.out.println("entro nel put");
		ErroreDTO errore = (ErroreDTO)request.getAttribute("dtoAttr");
		return ResponseEntity.status(401).body(errore);
	}
	@PostMapping("/errore")
	public ResponseEntity<DTO> errorePost(ServletRequest request){
		System.out.println("entro nel post");
		ErroreDTO errore = (ErroreDTO)request.getAttribute("dtoAttr");
		return ResponseEntity.status(401).body(errore);
	}
	@DeleteMapping("/errore")
	public ResponseEntity<DTO> erroreDelete(ServletRequest request){
		System.out.println("entro nel delete");
		ErroreDTO errore = (ErroreDTO)request.getAttribute("dtoAttr");
		return ResponseEntity.status(401).body(errore);
	}
	
}
