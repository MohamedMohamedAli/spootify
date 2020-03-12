package it.spootify.spootify.web.rest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.spootify.spootify.model.Artista;
import it.spootify.spootify.service.ArtistaService;
import it.spootify.spootify.web.dto.ArtistaDTO;
import it.spootify.spootify.web.dto.ConfermaDTO;

@RestController
@RequestMapping(value = "/artista")
public class ArtistaResource {
	
	@Autowired
	private ArtistaService artistaService;
	@GetMapping
	public ResponseEntity<List<ArtistaDTO>> listAll(){
		List<ArtistaDTO> listaArtistaDTO = Artista.buildListDTO(artistaService.listAll());
		return ResponseEntity.ok(listaArtistaDTO);
	}
	@GetMapping("/dettaglio/{id}")
	public ResponseEntity<ArtistaDTO> dettaglio(@PathVariable("id")Long id){
		ArtistaDTO artistaDTO = artistaService.caricaConIdEager(id).buildDTO(true);
		return ResponseEntity.ok(artistaDTO);
	}
	@PostMapping("/admin/insert")
	public ResponseEntity<ArtistaDTO> insert(@RequestBody ArtistaDTO artistaDTO){
		Artista artista = artistaDTO.buildModel(false);
		artistaService.inserisci(artista);
		return ResponseEntity.ok(artistaDTO);
	}
	@PutMapping("/admin/modifica/{id}")
	public ResponseEntity<ArtistaDTO> modifica(@RequestBody ArtistaDTO artistaDTO, @PathVariable("id")Long id){
		artistaDTO.setId(id);
		Artista artista = artistaDTO.buildModel(false);
		artistaService.aggiorna(artista);
		return ResponseEntity.ok(artistaDTO);
	}
	@DeleteMapping("/admin/elimina/{id}")
	public ResponseEntity<ConfermaDTO> elimina(@PathVariable("id")Long id){
		artistaService.elimina(id);
		return ResponseEntity.ok(new ConfermaDTO("artista ["+id+"] eliminato con successo"));
	}
}
