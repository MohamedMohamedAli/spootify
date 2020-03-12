package it.spootify.spootify.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.spootify.spootify.web.dto.AlbumDTO;

@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date dataUscita;
	private String titolo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artista_id")
	private Artista artista;
	@OneToMany(mappedBy = "album", orphanRemoval = true)
	private List<Brano> brani = new ArrayList<Brano>();
	@OneToMany(mappedBy = "album")
	private List<Riproduzione> riproduzione = new ArrayList<Riproduzione>();
	public Album() {
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
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
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
	
	public AlbumDTO buildDTO(boolean eagerArtista, boolean eagerBrani, boolean eagerRiproduzione) {
		AlbumDTO albumDTO = new AlbumDTO();
		albumDTO.setId(this.id);
		albumDTO.setTitolo(this.titolo);
		if(eagerArtista) {
			albumDTO.setArtista(this.artista.buildDTO(false));
		}
		if(eagerBrani) {
			albumDTO.setBrani(Brano.buildListDTO(this.brani));
		}
		if(eagerRiproduzione) {
			albumDTO.setRiproduzione(Riproduzione.buildListDTO(this.riproduzione));
		}
		return albumDTO;
	}
	public static List<AlbumDTO> buildListDTO(List<Album> album){
		List<AlbumDTO>albumDTO = new ArrayList<AlbumDTO>();
		for(Album a:album) {
			albumDTO.add(a.buildDTO(false,false,false));
		}
		return albumDTO;
	}

}
