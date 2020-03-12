package it.spootify.spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spootify.spootify.model.Riproduzione;
import it.spootify.spootify.repository.RiproduzioneRepository;

@Service
public class RiproduzioneServiceImpl implements RiproduzioneService{

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private RiproduzioneRepository riproduzioneRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Riproduzione> listAll() {
		// TODO Auto-generated method stub
		return (List<Riproduzione>)riproduzioneRepository.findAll();
	}
	@Transactional(readOnly=true)
	@Override
	public Riproduzione caricaConId(Long id) {
		// TODO Auto-generated method stub
		return riproduzioneRepository.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public void inserisci(Riproduzione input) {
		// TODO Auto-generated method stub
		riproduzioneRepository.save(input);
	}
	@Transactional
	@Override
	public void aggiorna(Riproduzione input) {
		// TODO Auto-generated method stub
		riproduzioneRepository.save(input);
	}
	@Transactional
	@Override
	public void elimina(Long id) {
		// TODO Auto-generated method stub
		riproduzioneRepository.deleteById(id);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Riproduzione> cercaExample(Riproduzione example) {
		// TODO Auto-generated method stub
		return null;
	}

}
