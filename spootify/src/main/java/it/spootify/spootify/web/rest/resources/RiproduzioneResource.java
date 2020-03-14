package it.spootify.spootify.web.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.spootify.spootify.model.Brano;
import it.spootify.spootify.model.Riproduzione;
import it.spootify.spootify.service.RiproduzioneService;
import it.spootify.spootify.web.dto.RiproduzioneDTO;
import it.spootify.spootify.web.dto.RiproduzioneProvaDTO;

@RestController
@RequestMapping(value = "/riproduzione")
public class RiproduzioneResource {
	
	@Autowired
	private RiproduzioneService riproduzioneService;
	
	@PostMapping
	public ResponseEntity<RiproduzioneDTO> avviaRiproduzione(@RequestBody RiproduzioneProvaDTO example){
		Riproduzione riproduzione = riproduzioneService.avviaRiproduzione(example);
		RiproduzioneDTO riproduzioneDTO = riproduzione.buildDTO(true, true, true, true);
		if(riproduzione.getAlbum()!=null) {
			riproduzioneDTO.getAlbum().setBrani(Brano.buildListDTO(riproduzione.getAlbum().getBrani()));
		}else {
			riproduzioneDTO.getPlaylist().setBrani(Brano.buildListDTO(riproduzione.getPlaylist().getBrani()));
		}
		return ResponseEntity.ok(riproduzioneDTO);
	}
	
}
