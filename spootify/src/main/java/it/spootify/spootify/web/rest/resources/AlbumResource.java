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

import it.spootify.spootify.model.Album;
import it.spootify.spootify.service.AlbumService;
import it.spootify.spootify.web.dto.AlbumDTO;
import it.spootify.spootify.web.dto.ConfermaDTO;

@RestController
@RequestMapping(value = "/album")
public class AlbumResource {
	@Autowired
	private AlbumService albumService;
	
	@GetMapping
	public ResponseEntity<List<AlbumDTO>> listAll(){
		List<AlbumDTO> listaAlbumDTO = Album.buildListDTO(albumService.listAll());
		return ResponseEntity.ok(listaAlbumDTO);
	}
	@GetMapping("/dettaglio/{id}")
	public ResponseEntity<AlbumDTO> dettaglio(@PathVariable("id")Long id){
		AlbumDTO albumDTO = albumService.caricaConIdEager(id).buildDTO(true, true, false);
		return ResponseEntity.ok(albumDTO);
	}
	@PostMapping("/admin/insert")
	public ResponseEntity<AlbumDTO> insert(@RequestBody AlbumDTO albumDTO){
		Album album = albumDTO.buildModel(true, true, false);
		album.setDataUscita(albumDTO.getDataUscita());
		albumService.inserisci(album);
		return ResponseEntity.ok(albumDTO);
	}
	@PutMapping("/admin/modifica/{id}")
	public ResponseEntity<AlbumDTO> modifica(@RequestBody AlbumDTO albumDTO, @PathVariable("id")Long id){
		albumDTO.setId(id);
		Album album = albumDTO.buildModel(true, true, false);
		album.setDataUscita(albumDTO.getDataUscita());
		albumService.aggiorna(album);
		return ResponseEntity.ok(albumDTO);
	}
	@DeleteMapping("/admin/elimina/{id}")
	public ResponseEntity<ConfermaDTO> elimina(@PathVariable("id")Long id){
		albumService.elimina(id);
		return ResponseEntity.ok(new ConfermaDTO("album ["+id+"] eliminato con successo"));
	}
	
}
