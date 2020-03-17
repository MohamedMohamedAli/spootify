package it.spootify.spootify.web.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.spootify.spootify.model.Brano;
import it.spootify.spootify.model.Riproduzione;
import it.spootify.spootify.service.RiproduzioneService;
import it.spootify.spootify.web.dto.BranoDTO;
import it.spootify.spootify.web.dto.ConfermaDTO;
import it.spootify.spootify.web.dto.DTO;
import it.spootify.spootify.web.dto.RiproduzioneDTO;
import it.spootify.spootify.web.dto.RiproduzioneProvaDTO;

@RestController
@RequestMapping(value = "/riproduzione")
public class RiproduzioneResource {
	
	@Autowired
	private RiproduzioneService riproduzioneService;
	
	@PostMapping
	public ResponseEntity<RiproduzioneDTO> avviaRiproduzione( @RequestBody RiproduzioneProvaDTO example){
		Riproduzione riproduzione = riproduzioneService.avviaRiproduzione(example);
		RiproduzioneDTO riproduzioneDTO = riproduzione.buildDTO(true, true, true, true);
		if(riproduzione.getAlbum()!=null) {
			riproduzioneDTO.getAlbum().setBrani(Brano.buildListDTO(riproduzione.getAlbum().getBrani()));
			//carico dentro Album <---- artista
			riproduzioneDTO.getAlbum().setArtista(riproduzione.getAlbum().getArtista().buildDTO(false));
		}else {
			riproduzioneDTO.getPlaylist().setBrani(Brano.buildListDTO(riproduzione.getPlaylist().getBrani()));
			//carico dentro BRANO <---- album <----artista
			for(int i=0; i<riproduzioneDTO.getPlaylist().getBrani().size(); i++) {
				riproduzioneDTO.getPlaylist().getBrani().get(i).setAlbum(riproduzione.getPlaylist().getBrani().get(i).getAlbum().buildDTO(true, false, false));
			}
		}
		return ResponseEntity.ok(riproduzioneDTO);
	}
	
	@PutMapping("/cambiaBrano")
	public ResponseEntity<BranoDTO> cambiaBrano(@RequestBody RiproduzioneProvaDTO input){
		
		BranoDTO branoDTO = riproduzioneService.cambiaBrano(input).buildDTO(false, false, false);
		
		return ResponseEntity.ok(branoDTO);
	}
	
	@DeleteMapping("/elimina")
	public ResponseEntity<DTO> elimina(@RequestBody Long id){
		riproduzioneService.elimina(id);
		return ResponseEntity.ok(new ConfermaDTO("Riproduzione ["+id+"] eliminata con successo"));
	}
}
