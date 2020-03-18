package it.spootify.spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spootify.spootify.model.Brano;
import it.spootify.spootify.model.Playlist;
import it.spootify.spootify.repository.BranoRepository;

@Service
public class BranoServiceImpl implements BranoService{
	
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private BranoRepository branoRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Brano> listAll() {
		// TODO Auto-generated method stub
		return (List<Brano>)branoRepository.findAll();
	}
	@Transactional(readOnly=true)
	@Override
	public Brano caricaConId(Long id) {
		// TODO Auto-generated method stub
		return branoRepository.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public void inserisci(Brano input) {
		// TODO Auto-generated method stub
		branoRepository.save(input);
	}
	@Transactional
	@Override
	public void aggiorna(Brano input) {
		// TODO Auto-generated method stub
		branoRepository.save(input);
	}
	@Transactional
	@Override
	public void elimina(Long id) {
		// TODO Auto-generated method stub
		System.out.println("entro elimina brano");
		Brano brano = branoRepository.findById(id).orElse(null);
		for(Playlist p:brano.getListaPlaylist()) {
			p.getBrani().remove(brano);
		}
		branoRepository.deleteById(id);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Brano> cercaExample(Brano example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public Brano caricaConIdEager(Long id) {
		// TODO Auto-generated method stub
		return branoRepository.findByIdEager(id);
	}

}
