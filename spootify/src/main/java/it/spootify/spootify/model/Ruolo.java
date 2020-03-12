package it.spootify.spootify.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import it.spootify.spootify.web.dto.RuoloDTO;


@Entity
public class Ruolo {

	
	public static final String ADMIN = "ADMIN";
	public static final String CUSTOMER = "CUSTOMER";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descrizione;
	private String codice;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ruoli")
	private Set<Utente>utenti = new HashSet<Utente>();

	public Ruolo() {
	}

	public Ruolo(String descrizione, String codice) {
		this.descrizione = descrizione;
		this.codice = codice;
	}
	
	public Ruolo(Long id, String codice, String descrizione) {
		this.id = id;
		this.descrizione = descrizione;
		this.codice = codice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public static List<Ruolo> ottieniRuoli() {
		List<Ruolo>ruoli = new ArrayList<Ruolo>();
		Ruolo ruoloAdmin = new Ruolo(1l,"codice1",ADMIN);
		Ruolo ruoloCustomer = new Ruolo(2l,"codice2",CUSTOMER);
		ruoli.add(ruoloAdmin);
		ruoli.add(ruoloCustomer);
		return ruoli;
	}
	public RuoloDTO buildDTO() {
		RuoloDTO ruoloDTO = new RuoloDTO();
		ruoloDTO.setId(this.id);
		ruoloDTO.setCodice(this.codice);
		ruoloDTO.setDescrizione(this.descrizione);
		return ruoloDTO;
	}
	public static Set<RuoloDTO> buildListDTO(Set<Ruolo>ruoli){
		Set<RuoloDTO>ruoliDTO = new HashSet<RuoloDTO>();
		for(Ruolo r:ruoli) {
			ruoliDTO.add(r.buildDTO());
		}
		return ruoliDTO;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("id: "+this.id+
				"\n codice: "+this.codice+
				"\n descrizione: "+this.descrizione);
	}
	
}
