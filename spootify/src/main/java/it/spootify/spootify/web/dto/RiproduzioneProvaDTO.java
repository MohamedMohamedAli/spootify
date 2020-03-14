package it.spootify.spootify.web.dto;

public class RiproduzioneProvaDTO {
	
	private String idAlbum;
	private String idPlaylist;
	private String codice;
	
	public RiproduzioneProvaDTO() {
		super();
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
	
	
	
}
