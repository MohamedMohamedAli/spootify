package it.spootify.spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spootify.spootify.model.Playlist;
import it.spootify.spootify.model.Utente;
import it.spootify.spootify.repository.PlaylistRepository;
import it.spootify.spootify.repository.UtenteRepository;

@Service
public class PlaylistServiceImpl implements PlaylistService{

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private PlaylistRepository playlistRepository;
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Playlist> listAll() {
		// TODO Auto-generated method stub
		return (List<Playlist>)playlistRepository.findAll();
	}
	@Transactional(readOnly=true)
	@Override
	public Playlist caricaConId(Long id) {
		// TODO Auto-generated method stub
		return playlistRepository.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public void inserisci(Playlist input) {
		// TODO Auto-generated method stub
		playlistRepository.save(input);
	}
	@Transactional
	@Override
	public void aggiorna(Playlist input) {
		// TODO Auto-generated method stub
		Utente creatore = utenteRepository.caricaUtenteConCodice(input.getCreatore().getNome());
		Playlist playlist = playlistRepository.findById(input.getId()).orElse(null);
		if(creatore.getId()==playlist.getCreatore().getId()) {
			input.setCreatore(creatore);
			playlistRepository.save(input);
		}
	}
	@Transactional
	@Override
	public void elimina(Long id) {
		// TODO Auto-generated method stub
		playlistRepository.deleteById(id);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Playlist> cercaExample(Playlist example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(readOnly=true)
	@Override
	public Playlist caricaConIdEager(Long id) {
		// TODO Auto-generated method stub
		Playlist playlist = playlistRepository.findByIdEager(id);
		return playlist;
	}
	@Transactional
	@Override
	public void crea(Playlist playlist, String codice) {
		// TODO Auto-generated method stub
		Utente utente = utenteRepository.caricaUtenteConCodice(codice);
		playlist.setCreatore(utente);
		playlistRepository.save(playlist);
	}

}
