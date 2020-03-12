package it.spootify.spootify.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.spootify.spootify.web.dto.UtenteDTO;


@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date dataRegistrazione;
	@Enumerated(EnumType.STRING)
	private StatoUtente stato = StatoUtente.CREATO;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sessione_id")
	private Sessione sessione;
	@ManyToMany(cascade ={CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<Ruolo>();
	@OneToMany(mappedBy = "creatore", orphanRemoval = true)
	private List<Playlist> listaPlaylist = new ArrayList <Playlist>();
	@OneToMany(mappedBy = "customer")
	private List<Riproduzione> riproduzione = new ArrayList<Riproduzione>();
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "tavoloGioco_id")
	
	public Utente() {
	}

	public Utente(String nome, String cognome, String username, String password, Date dataRegistrazione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
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
	public Set<Ruolo> getRuoli() {
		return ruoli;
	}
	public Sessione getSessione() {
		return sessione;
	}

	public void setSessione(Sessione sessione) {
		this.sessione = sessione;
	}

	public List<Playlist> getListaPlaylist() {
		return listaPlaylist;
	}

	public void setListaPlaylist(List<Playlist> listaPlaylist) {
		this.listaPlaylist = listaPlaylist;
	}

	public List<Riproduzione> getRiproduzione() {
		return riproduzione;
	}

	public void setRiproduzione(List<Riproduzione> riproduzione) {
		this.riproduzione = riproduzione;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public boolean isAdmin() {
		for (Ruolo ruoloItem : this.ruoli) {
			if (ruoloItem.getDescrizione().equals(Ruolo.ADMIN))
				return true;
		}
		return false;
	}
	public boolean isCustomer() {
		for (Ruolo ruoloItem : this.ruoli) {
			if (ruoloItem.getDescrizione().equals(Ruolo.CUSTOMER))
				return true;
		}
		return false;
	}
	
	public UtenteDTO buildDTO(boolean eagerSessione, boolean eagerRuoli, boolean eagerPlaylist, boolean eagerRiproduzione) {
		UtenteDTO utenteDTO = new UtenteDTO();
		utenteDTO.setId(this.id);
		utenteDTO.setNome(this.nome);
		utenteDTO.setCognome(this.cognome);
		utenteDTO.setStato(this.stato);
		utenteDTO.setDataRegistrazione(this.dataRegistrazione);
		utenteDTO.setUsername(this.username);
		utenteDTO.setPassword(this.password);
		if(eagerSessione) {
			utenteDTO.setSessione(this.sessione.buildDTO());
		}
		if(eagerRuoli) {
			utenteDTO.setRuoli(Ruolo.buildListDTO(this.ruoli));
		}
		if(eagerPlaylist) {
			utenteDTO.setListaPlaylist(Playlist.buildListDTO(this.listaPlaylist));
		}
		if(eagerRiproduzione) {
			utenteDTO.setRiproduzione(Riproduzione.buildListDTO(this.riproduzione));
		}
		return utenteDTO;
	}
	public static List<UtenteDTO> buildListDTO(List<Utente> utenti){
		List<UtenteDTO>utentiDTO = new ArrayList<UtenteDTO>();
		for(Utente u:utenti) {
			utentiDTO.add(u.buildDTO(false,false,false,false));
		}
		return utentiDTO;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("\n id: "+this.id+
				"\n nome: "+this.nome+
				"\n cognome: "+this.cognome+
				"\n username: "+this.username+
				"\n password: "+this.password);
	}

	
}
