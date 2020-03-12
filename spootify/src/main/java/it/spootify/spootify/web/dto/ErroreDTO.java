package it.spootify.spootify.web.dto;

public class ErroreDTO extends DTO{
	private String errore;

	public ErroreDTO() {
		super();
	}
	public ErroreDTO(String errore) {
		super();
		this.errore = errore;
	}

	public String getErrore() {
		return errore;
	}

	public void setErrore(String errore) {
		this.errore = errore;
	}
	
}
