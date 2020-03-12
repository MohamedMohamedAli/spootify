package it.spootify.spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spootify.spootify.model.Playlist;
import it.spootify.spootify.repository.PlaylistRepository;

@Service
public class PlaylistServiceImpl implements PlaylistService{

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private PlaylistRepository playlistRepository;
	
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
		playlistRepository.save(input);
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

}
