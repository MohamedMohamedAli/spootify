package it.spootify.spootify.web.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.spootify.model.Sessione;

public class SessioneDTO {
	
	private Long id;
	private String codice;
	private Date dataInizioSessione;
	@JsonIgnoreProperties(value= {"sessione"})
	private UtenteDTO utente;
	public SessioneDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public Date getDataInizioSessione() {
		return dataInizioSessione;
	}
	public void setDataInizioSessione(Date dataInizioSessione) {
		this.dataInizioSessione = dataInizioSessione;
	}
	public UtenteDTO getUtente() {
		return utente;
	}
	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}
	
	public Sessione buildModel() {
		Sessione sessione = new Sessione();
		sessione.setId(this.id);
		sessione.setCodice(this.codice);
		sessione.setDataInizioSessione(this.dataInizioSessione);
		return sessione;
	}
	
}
