package it.spootify.spootify.web.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.spootify.model.Ruolo;

public class RuoloDTO {
	public static final String ADMIN = "ADMIN";
	public static final String CUSTOMER = "CUSTOMER";
	
	private Long id;
	private String descrizione;
	private String codice;
	@JsonIgnoreProperties(value= {"ruoli"})
	private Set<UtenteDTO>utenti = new HashSet<UtenteDTO>();
	public RuoloDTO() {
		super();
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
	public Set<UtenteDTO> getUtenti() {
		return utenti;
	}
	public void setUtenti(Set<UtenteDTO> utenti) {
		this.utenti = utenti;
	}
	public static String getAdmin() {
		return ADMIN;
	}
	public static String getCustomer() {
		return CUSTOMER;
	}
	
	public Ruolo buildModel() {
		Ruolo ruolo = new Ruolo();
		ruolo.setId(this.id);
		ruolo.setCodice(this.codice);
		ruolo.setDescrizione(this.descrizione);
		return ruolo;
	}
	public static Set<Ruolo> buildListModel(Set<RuoloDTO>ruoliDTO) {
		Set<Ruolo>ruoli = new HashSet<Ruolo>();
		for(RuoloDTO r:ruoliDTO) {
			ruoli.add(r.buildModel());
		}
		return ruoli;
	}
	
}
