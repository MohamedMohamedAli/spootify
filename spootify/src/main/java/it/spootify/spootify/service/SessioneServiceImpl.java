package it.spootify.spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spootify.spootify.model.Sessione;
import it.spootify.spootify.repository.SessioneRepository;

@Service
public class SessioneServiceImpl implements SessioneService{

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private SessioneRepository sessioneRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Sessione> listAll() {
		// TODO Auto-generated method stub
		return (List<Sessione>)sessioneRepository.findAll();
	}
	@Transactional(readOnly=true)
	@Override
	public Sessione caricaConId(Long id) {
		// TODO Auto-generated method stub
		return sessioneRepository.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public void inserisci(Sessione input) {
		// TODO Auto-generated method stub
		sessioneRepository.save(input);
	}
	@Transactional
	@Override
	public void aggiorna(Sessione input) {
		// TODO Auto-generated method stub
		sessioneRepository.save(input);
	}
	@Transactional
	@Override
	public void elimina(Long id) {
		// TODO Auto-generated method stub
		sessioneRepository.deleteById(id);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Sessione> cercaExample(Sessione example) {
		// TODO Auto-generated method stub
		return null;
	}

}
