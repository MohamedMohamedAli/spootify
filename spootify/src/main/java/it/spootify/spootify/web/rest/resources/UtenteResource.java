package it.spootify.spootify.web.rest.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.spootify.spootify.model.Utente;
import it.spootify.spootify.service.UtenteService;
import it.spootify.spootify.web.dto.ConfermaDTO;
import it.spootify.spootify.web.dto.UtenteDTO;

@RestController
@RequestMapping(value = "/utente")
public class UtenteResource {
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/admin")
	public ResponseEntity<List<UtenteDTO>> listAll(){
		List<Utente>utenti = utenteService.listAll();
		List<UtenteDTO> utentiDTO = Utente.buildListDTO(utenti);
		
		return ResponseEntity.ok(utentiDTO);
	}
	@GetMapping("/admin/dettaglio/{id}")
	public ResponseEntity<UtenteDTO> dettaglio(@PathVariable("id")Long id){
		UtenteDTO utenteDTO = utenteService.caricaConIdEager(id).buildDTO(false, true, true, false);
		return ResponseEntity.ok(utenteDTO);
	}
	@PostMapping("/insert")
	public ResponseEntity<UtenteDTO> insert(@RequestBody UtenteDTO utenteDTO){
		Utente utente = utenteDTO.buildModel(false,true,true,false);
		utente.setDataRegistrazione(new Date());
		utenteService.inserisci(utente);
		return ResponseEntity.ok(utenteDTO);
	}
	@PutMapping("/admin/modifica/{id}")
	public ResponseEntity<UtenteDTO> modifica(@RequestBody UtenteDTO utenteDTO, @PathVariable("id")Long id){
		utenteDTO.setId(id);
		Utente utente = utenteDTO.buildModel(false, true, true, false);
		utenteService.aggiorna(utente);
		return ResponseEntity.ok(utenteDTO);
	}
	@PutMapping("/admin/stato/{id}")
	public ResponseEntity<ConfermaDTO> cambiaStato(@PathVariable("id")Long id){
		String stato = utenteService.cambiaStato(id);
		return ResponseEntity.ok(new ConfermaDTO("L'utente ["+id+"] è stato "+stato+" ."));
	}
}
