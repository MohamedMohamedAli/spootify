package it.spootify.spootify.web.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.spootify.model.Album;

public class AlbumDTO {

	private Long id;
	private Date dataUscita;
	private String titolo;
	
	@JsonIgnoreProperties(value= {"listaAlbum"})
	private ArtistaDTO artista;
	@JsonIgnoreProperties(value= {"album"})
	private List<BranoDTO> brani = new ArrayList<BranoDTO>();
	@JsonIgnoreProperties(value= {"album"})
	private List<RiproduzioneDTO> riproduzione = new ArrayList<RiproduzioneDTO>();
	public AlbumDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataUscita() {
		return dataUscita;
	}
	public void setDataUscita(Date dataUscita) {
		this.dataUscita = dataUscita;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public ArtistaDTO getArtista() {
		return artista;
	}
	public void setArtista(ArtistaDTO artista) {
		this.artista = artista;
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
	
	public Album buildModel(boolean eagerArtista, boolean eagerBrani, boolean eagerRiproduzione) {
		Album album = new Album();
		album.setId(this.id);
		album.setTitolo(this.titolo);
		if(eagerArtista) {
			album.setArtista(this.artista.buildModel(false));
		}
		if(eagerBrani) {
			album.setBrani(BranoDTO.buildListModel(this.brani));
		}
		if(eagerRiproduzione) {
			album.setRiproduzione(RiproduzioneDTO.buildListModel(this.riproduzione));
		}
		return album;
	}
	public static List<Album> buildListModel(List<AlbumDTO> albumDTO){
		List<Album>album = new ArrayList<Album>();
		for(AlbumDTO a:albumDTO) {
			album.add(a.buildModel(false,false,false));
		}
		return album;
	}
	
}
