package it.spootify.spootify.service;

import it.spootify.spootify.model.Utente;
import it.spootify.spootify.web.dto.UtenteDTO;

public interface UtenteService extends IBaseService<Utente>{

	public Utente caricaConIdEager(Long id);

	public String cambiaStato(Long id);

	public String eseguiAcesso(UtenteDTO utenteDTO);
	
	public String logout(String codiceSessione);
	
	public String utenteSessione(String codiceSessione);
	
	public String utenteAdmin(String codiceSessione);

}
