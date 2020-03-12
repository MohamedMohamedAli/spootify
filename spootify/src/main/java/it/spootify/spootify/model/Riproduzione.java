package it.spootify.spootify.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import it.spootify.spootify.web.dto.RiproduzioneDTO;

@Entity
public class Riproduzione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brano_id")
	private Brano brano;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "playlist_id")
	private Playlist playlist;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "album_id")
	private Album album;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Utente customer;
	
	public Riproduzione() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Brano getBrano() {
		return brano;
	}
	public void setBrano(Brano brano) {
		this.brano = brano;
	}
	public Playlist getPlaylist() {
		return playlist;
	}
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public Utente getCustomer() {
		return customer;
	}
	public void setCustomer(Utente customer) {
		this.customer = customer;
	}
	
	public RiproduzioneDTO buildDTO(boolean eagerBrano, boolean eagerPlaylist, boolean eagerAlbum, boolean eagerCustomer) {
		RiproduzioneDTO riproduzioneDTO = new RiproduzioneDTO();
		riproduzioneDTO.setId(this.id);
		if(eagerBrano) {
			riproduzioneDTO.setBrano(this.brano.buildDTO(false,false,false));
		}
		if(eagerPlaylist) {
			riproduzioneDTO.setPlaylist(this.playlist.buildDTO(false,false,false));
		}
		if(eagerAlbum) {
			riproduzioneDTO.setAlbum(this.album.buildDTO(false,false,false));
		}
		if(eagerCustomer) {
			riproduzioneDTO.setCustomer(this.customer.buildDTO(false,false,false,false));
		}
		return riproduzioneDTO;
	}
	public static List<RiproduzioneDTO> buildListDTO(List<Riproduzione> riproduzione){
		List<RiproduzioneDTO> riproduzioneDTO = new ArrayList<RiproduzioneDTO>();
		for(Riproduzione r:riproduzione) {
			riproduzioneDTO.add(r.buildDTO(false,false,false,false));
		}
		return riproduzioneDTO;
	}
	
}
