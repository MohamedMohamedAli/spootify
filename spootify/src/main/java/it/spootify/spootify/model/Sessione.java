package it.spootify.spootify.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import it.spootify.spootify.web.dto.SessioneDTO;

@Entity
public class Sessione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codice;
	private Date dataInizioSessione;
	@OneToOne(mappedBy = "sessione")
	private Utente utente;
	public Sessione() {
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
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public SessioneDTO buildDTO() {
		SessioneDTO sessioneDTO = new SessioneDTO();
		sessioneDTO.setId(this.id);
		sessioneDTO.setCodice(this.codice);
		sessioneDTO.setDataInizioSessione(this.dataInizioSessione);
		return sessioneDTO;
	}
}
