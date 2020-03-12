package it.spootify.spootify.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spootify.spootify.model.Ruolo;
import it.spootify.spootify.model.Sessione;
import it.spootify.spootify.model.StatoUtente;
import it.spootify.spootify.model.Utente;
import it.spootify.spootify.repository.SessioneRepository;
import it.spootify.spootify.repository.UtenteRepository;
import it.spootify.spootify.web.dto.DTO;
import it.spootify.spootify.web.dto.UtenteDTO;

@Service
public class UtenteServiceImpl implements UtenteService{

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private UtenteRepository utenteRepository;
	@Autowired
	private SessioneRepository sessioneRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Utente> listAll() {
		// TODO Auto-generated method stub
		return (List<Utente>)utenteRepository.findAll();
	}
	@Transactional(readOnly=true)
	@Override
	public Utente caricaConId(Long id) {
		// TODO Auto-generated method stub
		return utenteRepository.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public void inserisci(Utente input) {
		// TODO Auto-generated method stub
		utenteRepository.save(input);
	}
	@Transactional
	@Override
	public void aggiorna(Utente input) {
		// TODO Auto-generated method stub
		Utente utente = utenteRepository.findById(input.getId()).orElse(null);
		input.setStato(utente.getStato());
		input.setDataRegistrazione(utente.getDataRegistrazione());
		utenteRepository.save(input);
	}
	@Transactional
	@Override
	public void elimina(Long id) {
		// TODO Auto-generated method stub
		utenteRepository.deleteById(id);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Utente> cercaExample(Utente example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(readOnly=true)
	@Override
	public Utente caricaConIdEager(Long id) {
		// TODO Auto-generated method stub
		return utenteRepository.findByIdEager(id);
	}
	@Transactional
	@Override
	public String cambiaStato(Long id) {
		// TODO Auto-generated method stub
		Utente utente = caricaConId(id);
		String stato ="";
		if(utente.getStato().equals(StatoUtente.ATTIVO)) {
			utente.setStato(StatoUtente.DISABILITATO);
			stato = "disabilitato";
		}else {
			utente.setStato(StatoUtente.ATTIVO);
			stato = "attivato";
		}
		utenteRepository.save(utente);
		return stato;
	}
	@Transactional
	@Override
	public String eseguiAcesso(UtenteDTO utenteDTO) {
		Utente utente = utenteRepository.eseguiAccesso(utenteDTO.getUsername(),utenteDTO.getPassword());
		if(utente == null) {
			return DTO.NO;
		}
		if(utente.getStato().equals(StatoUtente.DISABILITATO)) {
			return DTO.DISABILITATO;
		}
		if(utente.getStato().equals(StatoUtente.CREATO)) {
			return DTO.CREATO;
		}
		System.out.println(utente.getSessione());
		if(utente.getSessione()!=null) {
			System.out.println("id sessione: "+utente.getSessione().getId());
			System.out.println("tempo: "+(new Date().getTime()-utente.getSessione().getDataInizioSessione().getTime())/1000);
			sessioneRepository.deleteById(utente.getSessione().getId());
		}
		Sessione sessione = new Sessione();
		sessione.setCodice(UUID.randomUUID().toString());
		sessione.setDataInizioSessione(new Date());
		sessioneRepository.save(sessione);
		utente.setSessione(sessione);
		utenteRepository.save(utente);
		return sessione.getCodice();
	}
	@Transactional
	@Override
	public String logout(String codiceSessione) {
		// TODO Auto-generated method stub
		Utente utente = utenteRepository.caricaUtenteConCodice(codiceSessione);
		if(utente==null) {
			return DTO.NO;
		}
		sessioneRepository.deleteById(utente.getSessione().getId());
		utente.setSessione(null);
		utenteRepository.save(utente);
		return DTO.SI;
	}
	@Transactional
	@Override
	public String utenteSessione(String codiceSessione) {
		// TODO Auto-generated method stub
		Utente utente = utenteRepository.caricaUtenteConCodice(codiceSessione);
		if(utente == null) {
			return DTO.NO;
		}
		Long inizioSessione = utente.getSessione().getDataInizioSessione().getTime();
		Long adesso = new Date().getTime();
		Long differenzaSecondi = (adesso - inizioSessione)/1000 ;
		System.out.println("tempo sessione: "+differenzaSecondi);
		if(differenzaSecondi>300) {
			sessioneRepository.deleteById(utente.getSessione().getId());
			utente.setSessione(null);
			utenteRepository.save(utente);
			return DTO.SESSIONE_SCADUTA;
		}
		System.out.println("aggiorno tempo sessione");
		Sessione sessione = sessioneRepository.findById(utente.getSessione().getId()).orElse(null);
		sessione.setDataInizioSessione(new Date());
		sessioneRepository.save(sessione);
		return DTO.SI;
	}
	@Transactional(readOnly=true)
	@Override
	public String utenteAdmin(String codiceSessione) {
		// TODO Auto-generated method stub
		Utente utente = utenteRepository.caricaUtenteConCodice(codiceSessione);
		System.out.println("utente: "+utente);
		System.out.println("ruoli: "+utente.getRuoli().size());
		for(Ruolo r:utente.getRuoli()) {
			System.out.println("descrizione ruolo: "+r.getDescrizione());
			if(r.getDescrizione().equals(Ruolo.ADMIN)) {
				return DTO.SI;
			}
		}
		return DTO.NO;
	}
	
}
