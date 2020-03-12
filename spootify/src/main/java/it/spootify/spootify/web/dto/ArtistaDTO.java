package it.spootify.spootify.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.spootify.spootify.model.Artista;

public class ArtistaDTO {

	private Long id;
	private String nome;
	private String cognome;
	@JsonIgnoreProperties(value= {"artista"})
	private List<AlbumDTO>listaAlbum = new ArrayList<AlbumDTO>();
	public ArtistaDTO() {
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
	public List<AlbumDTO> getListaAlbum() {
		return listaAlbum;
	}
	public void setListaAlbum(List<AlbumDTO> listaAlbum) {
		this.listaAlbum = listaAlbum;
	}
	
	public Artista buildModel(boolean eagerAlbum) {
		Artista artista = new Artista();
		artista.setId(this.id);
		artista.setNome(this.nome);
		artista.setCognome(this.cognome);
		if(eagerAlbum) {
			artista.setListaAlbum(AlbumDTO.buildListModel(this.listaAlbum));
		}
		return artista;
	}
	public static List<Artista> buildListModel(List<ArtistaDTO> artistiDTO){
		List<Artista>artisti = new ArrayList<Artista>();
		for(ArtistaDTO a:artistiDTO) {
			artisti.add(a.buildModel(false));
		}
		return artisti;
	}
	
}
