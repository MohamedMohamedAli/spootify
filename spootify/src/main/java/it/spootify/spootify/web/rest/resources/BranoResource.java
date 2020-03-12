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

import it.spootify.spootify.model.Brano;
import it.spootify.spootify.service.BranoService;
import it.spootify.spootify.web.dto.BranoDTO;
import it.spootify.spootify.web.dto.ConfermaDTO;

@RestController
@RequestMapping(value = "/brano")
public class BranoResource {
	
	@Autowired
	private BranoService branoService;
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@GetMapping
	public ResponseEntity<List<BranoDTO>> listAll(){
		System.out.println("codice: "+httpServletRequest.getHeader("codice"));
		List<BranoDTO> listaBraniDTO = Brano.buildListDTO(branoService.listAll());
		return ResponseEntity.ok(listaBraniDTO);
	}
	@GetMapping("/dettaglio/{id}")
	public ResponseEntity<BranoDTO> dettaglio(@PathVariable("id")Long id){
		BranoDTO branoDTO = branoService.caricaConIdEager(id).buildDTO(true, false, false);
		return ResponseEntity.ok(branoDTO);
	}
	@PostMapping("/admin/insert")
	public ResponseEntity<BranoDTO> insert(@RequestBody BranoDTO branoDTO){
		Brano brano = branoDTO.buildModel(true,true,true);
		branoService.inserisci(brano);
		return ResponseEntity.ok(branoDTO);
	}
	@PutMapping("/admin/modifica/{id}")
	public ResponseEntity<BranoDTO> modifica(@RequestBody BranoDTO branoDTO, @PathVariable("id")Long id){
		branoDTO.setId(id);
		Brano brano = branoDTO.buildModel(true, true, true);
		branoService.aggiorna(brano);
		return ResponseEntity.ok(branoDTO);
	}
	@DeleteMapping("/admin/elimina/{id}")
	public ResponseEntity<ConfermaDTO> elimina(@PathVariable("id")Long id){
		branoService.elimina(id);
		return ResponseEntity.ok(new ConfermaDTO("brano ["+id+"] eliminato con successo"));
	}
	
}
