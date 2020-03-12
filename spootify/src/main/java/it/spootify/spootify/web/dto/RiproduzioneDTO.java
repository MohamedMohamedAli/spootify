package it.spootify.spootify.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.spootify.model.Riproduzione;

public class RiproduzioneDTO {
	
	private Long id;
	@JsonIgnoreProperties(value= {"riproduzione"})
	private BranoDTO brano;
	@JsonIgnoreProperties(value= {"riproduzione"})
	private PlaylistDTO playlist;
	@JsonIgnoreProperties(value= {"riproduzione"})
	private AlbumDTO album;
	@JsonIgnoreProperties(value= {"riproduzione"})
	private UtenteDTO customer;
	public RiproduzioneDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BranoDTO getBrano() {
		return brano;
	}
	public void setBrano(BranoDTO brano) {
		this.brano = brano;
	}
	public PlaylistDTO getPlaylist() {
		return playlist;
	}
	public void setPlaylist(PlaylistDTO playlist) {
		this.playlist = playlist;
	}
	public AlbumDTO getAlbum() {
		return album;
	}
	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}
	public UtenteDTO getCustomer() {
		return customer;
	}
	public void setCustomer(UtenteDTO customer) {
		this.customer = customer;
	}
	
	public Riproduzione buildModel(boolean eagerBrano, boolean eagerPlaylist, boolean eagerAlbum, boolean eagerCustomer) {
		Riproduzione riproduzione = new Riproduzione();
		riproduzione.setId(this.id);
		if(eagerBrano) {
			riproduzione.setBrano(this.brano.buildModel(false,false,false));
		}
		if(eagerPlaylist) {
			riproduzione.setPlaylist(this.playlist.buildModel(false,false,false));
		}
		if(eagerAlbum) {
			riproduzione.setAlbum(this.album.buildModel(false,false,false));
		}
		if(eagerCustomer) {
			riproduzione.setCustomer(this.customer.buildModel(false,false,false,false));
		}
		return riproduzione;
	}
	public static List<Riproduzione> buildListModel(List<RiproduzioneDTO> riproduzioneDTO){
		List<Riproduzione> riproduzione = new ArrayList<Riproduzione>();
		for(RiproduzioneDTO r:riproduzioneDTO) {
			riproduzione.add(r.buildModel(false,false,false,false));
		}
		return riproduzione;
	}
}
