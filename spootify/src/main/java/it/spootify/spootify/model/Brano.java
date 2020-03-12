package it.spootify.spootify.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import it.spootify.spootify.web.dto.BranoDTO;

@Entity
public class Brano {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titolo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "album_id")
	private Album album;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "brani")
	private List<Playlist> listaPlaylist = new ArrayList<Playlist>();
	@OneToMany(mappedBy = "brano")
	private List<Riproduzione> riproduzione = new ArrayList<Riproduzione>();
	public Brano() {
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
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
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
	
	public BranoDTO buildDTO(boolean eagerAlbum, boolean eagerPlaylist, boolean eagerRiproduzione) {
		BranoDTO branoDTO = new BranoDTO();
		branoDTO.setId(this.id);
		branoDTO.setTitolo(this.titolo);
		if(eagerAlbum) {
			branoDTO.setAlbum(this.album.buildDTO(false, false, false));
		}
		if(eagerPlaylist) {
			branoDTO.setListaPlaylist(Playlist.buildListDTO(this.listaPlaylist));
		}
		if(eagerRiproduzione) {
			branoDTO.setRiproduzione(Riproduzione.buildListDTO(this.riproduzione));
		}
		return branoDTO;
	}
	public static List<BranoDTO> buildListDTO(List<Brano>brani){
		List<BranoDTO> braniDTO = new ArrayList<BranoDTO>();
		for(Brano b:brani) {
			braniDTO.add(b.buildDTO(false,false,false));
		}
		return braniDTO;
	}
	
}
