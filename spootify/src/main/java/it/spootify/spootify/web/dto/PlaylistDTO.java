package it.spootify.spootify.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.spootify.model.Playlist;

public class PlaylistDTO {
	
	private Long id;
	private String titolo;
	@JsonIgnoreProperties(value= {"listaPlaylist"})
	private UtenteDTO creatore;
	@JsonIgnoreProperties(value= {"listaPlaylist"})
	private List<BranoDTO>brani = new ArrayList<BranoDTO>();
	@JsonIgnoreProperties(value= {"playlist"})
	private List<RiproduzioneDTO> riproduzione = new ArrayList<RiproduzioneDTO>();
	public PlaylistDTO() {
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
	public UtenteDTO getCreatore() {
		return creatore;
	}
	public void setCreatore(UtenteDTO creatore) {
		this.creatore = creatore;
	}
	public List<BranoDTO> getBrani() {
		return brani;
	}
	public void setBrani(List<BranoDTO> brani) {
		this.brani = brani;
	}
	public List<RiproduzioneDTO> getRiproduzione() {
		return riproduzione;
	}
	public void setRiproduzione(List<RiproduzioneDTO> riproduzione) {
		this.riproduzione = riproduzione;
	}
	
	public Playlist buildModel(boolean eagerCreatore, boolean eagerBrani, boolean eagerRiproduzione) {
		Playlist playlist = new Playlist();
		playlist.setId(this.id);
		playlist.setTitolo(this.titolo);
		if(eagerCreatore) {
			playlist.setCreatore(this.creatore.buildModel(false,false,false,false));
		}
		if(eagerBrani) {
			playlist.setBrani(BranoDTO.buildListModel(this.brani));
		}
		if(eagerRiproduzione) {
			playlist.setRiproduzione(RiproduzioneDTO.buildListModel(this.riproduzione));
		}
		return playlist;
	}
	public static List<Playlist> buildListModel(List<PlaylistDTO> playlistDTO){
		List<Playlist>playlist = new ArrayList<Playlist>();
		for(PlaylistDTO p:playlistDTO) {
			playlist.add(p.buildModel(false,false,false));
		}
		return playlist;
	}
	
}
