package it.spootify.spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spootify.spootify.model.Artista;
import it.spootify.spootify.repository.ArtistaRepository;

@Service
public class ArtistaServiceImpl implements ArtistaService{

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ArtistaRepository artistaRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Artista> listAll() {
		// TODO Auto-generated method stub
		return (List<Artista>)artistaRepository.findAll();
	}
	@Transactional(readOnly=true)
	@Override
	public Artista caricaConId(Long id) {
		// TODO Auto-generated method stub
		return artistaRepository.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public void inserisci(Artista input) {
		// TODO Auto-generated method stub
		artistaRepository.save(input);
	}
	@Transactional
	@Override
	public void aggiorna(Artista input) {
		// TODO Auto-generated method stub
		artistaRepository.save(input);
	}
	@Transactional
	@Override
	public void elimina(Long id) {
		// TODO Auto-generated method stub
		artistaRepository.deleteById(id);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Artista> cercaExample(Artista example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(readOnly=true)
	@Override
	public Artista caricaConIdEager(Long id) {
		// TODO Auto-generated method stub
		return artistaRepository.findByIdEager(id);
	}

}
