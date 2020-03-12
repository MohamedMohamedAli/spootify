package it.spootify.spootify.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import it.spootify.spootify.web.dto.ArtistaDTO;

@Entity
public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	@OneToMany(mappedBy = "artista", orphanRemoval = true)
	private List<Album>listaAlbum = new ArrayList<Album>();
	public Artista() {
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
	public List<Album> getListaAlbum() {
		return listaAlbum;
	}
	public void setListaAlbum(List<Album> listaAlbum) {
		this.listaAlbum = listaAlbum;
	}
	
	public ArtistaDTO buildDTO(boolean eagerAlbum) {
		ArtistaDTO artistaDTO = new ArtistaDTO();
		artistaDTO.setId(this.id);
		artistaDTO.setNome(this.nome);
		artistaDTO.setCognome(this.cognome);
		if(eagerAlbum) {
			artistaDTO.setListaAlbum(Album.buildListDTO(this.listaAlbum));
		}
		return artistaDTO;
	}
	public static List<ArtistaDTO> buildListDTO(List<Artista> artisti){
		List<ArtistaDTO>artistiDTO = new ArrayList<ArtistaDTO>();
		for(Artista a:artisti) {
			artistiDTO.add(a.buildDTO(false));
		}
		return artistiDTO;
	}

}
