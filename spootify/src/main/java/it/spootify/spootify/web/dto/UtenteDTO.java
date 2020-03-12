package it.spootify.spootify.web.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.spootify.model.StatoUtente;
import it.spootify.spootify.model.Utente;

public class UtenteDTO {
	
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private Date dataRegistrazione;
	private StatoUtente stato = StatoUtente.CREATO;
	@JsonIgnoreProperties(value= {"utente"})
	private SessioneDTO sessione;
	@JsonIgnoreProperties(value= {"utenti"})
	private Set<RuoloDTO>ruoli = new HashSet<RuoloDTO>();
	@JsonIgnoreProperties(value= {"creatore"})
	private List<PlaylistDTO> listaPlaylist = new ArrayList<PlaylistDTO>();
	@JsonIgnoreProperties(value= {"customer"})
	private List<RiproduzioneDTO> riproduzione = new ArrayList<RiproduzioneDTO>();
	public UtenteDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}
	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	public StatoUtente getStato() {
		return stato;
	}
	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}
	public SessioneDTO getSessione() {
		return sessione;
	}
	public void setSessione(SessioneDTO sessione) {
		this.sessione = sessione;
	}
	public Set<RuoloDTO> getRuoli() {
		return ruoli;
	}
	public void setRuoli(Set<RuoloDTO> ruoli) {
		this.ruoli = ruoli;
	}
	public List<PlaylistDTO> getListaPlaylist() {
		return listaPlaylist;
	}
	public void setListaPlaylist(List<PlaylistDTO> listaPlaylist) {
		this.listaPlaylist = listaPlaylist;
	}
	public List<RiproduzioneDTO> getRiproduzione() {
		return riproduzione;
	}
	public void setRiproduzione(List<RiproduzioneDTO> riproduzione) {
		this.riproduzione = riproduzione;
	}
	
	public Utente buildModel(boolean eagerSessione, boolean eagerRuoli, boolean eagerPlaylist, boolean eagerRiproduzione) {
		Utente utente = new Utente();
		utente.setId(this.id);
		utente.setNome(this.nome);
		utente.setCognome(this.cognome);
		utente.setStato(this.stato);
		utente.setDataRegistrazione(this.dataRegistrazione);
		utente.setUsername(this.username);
		utente.setPassword(this.password);
		if(eagerSessione) {
			utente.setSessione(this.sessione.buildModel());
		}
		if(eagerRuoli) {
			utente.setRuoli(RuoloDTO.buildListModel(this.ruoli));
		}
		if(eagerPlaylist) {
			utente.setListaPlaylist(PlaylistDTO.buildListModel(this.listaPlaylist));
		}
		if(eagerRiproduzione) {
			utente.setRiproduzione(RiproduzioneDTO.buildListModel(this.riproduzione));
		}
		return utente;
	}
	public static List<Utente> buildListModel(List<UtenteDTO> utentiDTO){
		List<Utente>utenti = new ArrayList<Utente>();
		for(UtenteDTO u:utentiDTO) {
			utenti.add(u.buildModel(false,false,false,false));
		}
		return utenti;
	}
}
