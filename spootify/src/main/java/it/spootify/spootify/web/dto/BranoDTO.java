package it.spootify.spootify.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.spootify.model.Brano;

public class BranoDTO {
	
	private Long id;
	private String titolo;
	@JsonIgnoreProperties(value= {"brani"})
	private AlbumDTO album;
	@JsonIgnoreProperties(value= {"brani"})
	private List<PlaylistDTO> listaPlaylist = new ArrayList<PlaylistDTO>();
	@JsonIgnoreProperties(value= {"brano"})
	private List<RiproduzioneDTO> riproduzione = new ArrayList<RiproduzioneDTO>();
	public BranoDTO() {
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
	public AlbumDTO getAlbum() {
		return album;
	}
	public void setAlbum(AlbumDTO album) {
		this.album = album;
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
	
	public Brano buildModel(boolean eagerAlbum, boolean eagerPlaylist, boolean eagerRiproduzione) {
		Brano brano = new Brano();
		brano.setId(this.id);
		brano.setTitolo(this.titolo);
		if(eagerAlbum) {
			brano.setAlbum(this.album.buildModel(false,false,false));
		}
		if(eagerPlaylist) {
			brano.setListaPlaylist(PlaylistDTO.buildListModel(this.listaPlaylist));
		}
		if(eagerRiproduzione) {
			brano.setRiproduzione(RiproduzioneDTO.buildListModel(this.riproduzione));
		}
		return brano;
	}
	public static List<Brano> buildListModel(List<BranoDTO>braniDTO){
		List<Brano> brani = new ArrayList<Brano>();
		for(BranoDTO b:braniDTO) {
			brani.add(b.buildModel(false,false,false));
		}
		return brani;
	}
	
}
