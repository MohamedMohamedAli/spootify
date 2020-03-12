package it.spootify.spootify.web.dto;

public class ConfermaDTO extends DTO {
	private String conferma;

	public ConfermaDTO() {
		super();
	}
	public ConfermaDTO(String conferma) {
		super();
		this.conferma = conferma;
	}

	public String getConferma() {
		return conferma;
	}

	public void setConferma(String conferma) {
		this.conferma = conferma;
	}
	
}
