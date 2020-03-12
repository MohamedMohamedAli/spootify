package it.spootify.spootify.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import it.spootify.spootify.web.dto.PlaylistDTO;

@Entity
public class Playlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titolo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creatore_id")
	private Utente creatore;
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "playlist_brano", joinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "brano_id", referencedColumnName = "ID"))
	private List<Brano>brani = new ArrayList<Brano>();
	@OneToMany(mappedBy = "playlist")
	private List<Riproduzione> riproduzione = new ArrayList<Riproduzione>();
	
	public Playlist() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public Utente getCreatore() {
		return creatore;
	}
	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}
	public List<Brano> getBrani() {
		return brani;
	}
	public void setBrani(List<Brano> brani) {
		this.brani = brani;
	}
	public List<Riproduzione> getRiproduzione() {
		return riproduzione;
	}
	public void setRiproduzione(List<Riproduzione> riproduzione) {
		this.riproduzione = riproduzione;
	}
	
	public PlaylistDTO buildDTO(boolean eagerCreatore, boolean eagerBrani, boolean eagerRiproduzione) {
		PlaylistDTO playlistDTO = new PlaylistDTO();
		playlistDTO.setId(this.id);
		playlistDTO.setTitolo(this.titolo);
		if(eagerCreatore) {
			playlistDTO.setCreatore(this.creatore.buildDTO(false,false,false,false));
		}
		if(eagerBrani) {
			playlistDTO.setBrani(Brano.buildListDTO(this.brani));
		}
		if(eagerRiproduzione) {
			playlistDTO.setRiproduzione(Riproduzione.buildListDTO(this.riproduzione));
		}
		return playlistDTO;
	}
	public static List<PlaylistDTO> buildListDTO(List<Playlist> playlist){
		List<PlaylistDTO>playlistDTO = new ArrayList<PlaylistDTO>();
		for(Playlist p:playlist) {
			playlistDTO.add(p.buildDTO(false,false,false));
		}
		return playlistDTO;
	}
	
}
