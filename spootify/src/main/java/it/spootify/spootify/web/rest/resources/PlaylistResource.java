package it.spootify.spootify.web.rest.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import it.spootify.spootify.model.Playlist;
import it.spootify.spootify.model.Utente;
import it.spootify.spootify.service.PlaylistService;
import it.spootify.spootify.web.dto.ConfermaDTO;
import it.spootify.spootify.web.dto.PlaylistDTO;

@RestController
@RequestMapping(value = "/playlist")
public class PlaylistResource {
	
	@Autowired
	private PlaylistService playlistService;
	@Autowired
	private HttpServletRequest http;
	
	@GetMapping
	public ResponseEntity<List<PlaylistDTO>> listAll(){
		List<PlaylistDTO> listaPlaylistDTO = Playlist.buildListDTO(playlistService.listAll());
		return ResponseEntity.ok(listaPlaylistDTO);
	}
	@GetMapping("/dettaglio/{id}")
	public ResponseEntity<PlaylistDTO> dettaglio(@PathVariable("id")Long id){
		PlaylistDTO playlistDTO = playlistService.caricaConIdEager(id).buildDTO(true, true, false );
		return ResponseEntity.ok(playlistDTO);
	}
	@PostMapping("/insert")
	public ResponseEntity<PlaylistDTO> insert(@RequestBody PlaylistDTO playlistDTO){
		Playlist playlist = playlistDTO.buildModel(true,true,true);
		String codice = http.getHeader("codice");
		playlistService.crea(playlist,codice);
		return ResponseEntity.ok(playlistDTO);
	}
	@PutMapping("/modifica/{id}")
	public ResponseEntity<PlaylistDTO> modifica(@RequestBody PlaylistDTO playlistDTO, @PathVariable("id")Long id){
		playlistDTO.setId(id);
		Playlist playlist = playlistDTO.buildModel(true, true, true);
		Utente creatore = new Utente();
		creatore.setNome(http.getHeader("codice"));
		playlist.setCreatore(creatore);
		playlistService.aggiorna(playlist);
		return ResponseEntity.ok(playlistDTO);
	}
	@DeleteMapping("/elimina/{id}")
	public ResponseEntity<ConfermaDTO> elimina(@PathVariable("id")Long id){
		playlistService.elimina(id);
		return ResponseEntity.ok(new ConfermaDTO("playlist ["+id+"] eliminata con successo"));
	}
	
}
