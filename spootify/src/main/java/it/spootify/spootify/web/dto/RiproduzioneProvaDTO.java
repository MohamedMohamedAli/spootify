package it.spootify.spootify.web.dto;

public class RiproduzioneProvaDTO {
	
	private Long id;
	private String idAlbum;
	private String idPlaylist;
	private String codice;
	private String brano;
	
	public RiproduzioneProvaDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdAlbum() {
		return idAlbum;
	}
	public void setIdAlbum(String idAlbum) {
		this.idAlbum = idAlbum;
	}
	public String getIdPlaylist() {
		return idPlaylist;
	}
	public void setIdPlaylist(String idPlaylist) {
		this.idPlaylist = idPlaylist;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getBrano() {
		return brano;
	}
	public void setBrano(String brano) {
		this.brano = brano;
	}
	
	
	
	
}
