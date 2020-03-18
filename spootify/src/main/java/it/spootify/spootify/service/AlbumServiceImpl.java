package it.spootify.spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spootify.spootify.model.Album;
import it.spootify.spootify.model.Brano;
import it.spootify.spootify.repository.AlbumRepository;

@Service
public class AlbumServiceImpl implements AlbumService{

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private BranoService branoService;
	
	@Transactional(readOnly=true)
	@Override
	public List<Album> listAll() {
		// TODO Auto-generated method stub
		return (List<Album>)albumRepository.findAll();
	}
	@Transactional(readOnly=true)
	@Override
	public Album caricaConId(Long id) {
		// TODO Auto-generated method stub
		return albumRepository.findById(id).orElse(null);
	}
	@Transactional
	@Override
	public void inserisci(Album input) {
		// TODO Auto-generated method stub
		albumRepository.save(input);
	}
	@Transactional
	@Override
	public void aggiorna(Album input) {
		// TODO Auto-generated method stub
		albumRepository.save(input);
	}
	@Transactional
	@Override
	public void elimina(Long id) {
		// TODO Auto-generated method stub
		Album album = albumRepository.findByIdWithBrani(id);
		for(Brano b:album.getBrani()) {
			branoService.elimina(b.getId());
		}
		albumRepository.deleteById(id);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Album> cercaExample(Album example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(readOnly=true)
	@Override
	public Album caricaConIdEager(Long id) {
		// TODO Auto-generated method stub
		return albumRepository.findByIdEager(id);
	}

}
