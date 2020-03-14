package it.spootify.spootify.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.spootify.spootify.model.Album;
import it.spootify.spootify.model.Brano;
import it.spootify.spootify.model.Playlist;
import it.spootify.spootify.model.Riproduzione;
import it.spootify.spootify.model.Utente;
import it.spootify.spootify.repository.AlbumRepository;
import it.spootify.spootify.repository.PlaylistRepository;
import it.spootify.spootify.repository.RiproduzioneRepository;
import it.spootify.spootify.repository.UtenteRepository;
import it.spootify.spootify.web.dto.RiproduzioneProvaDTO;
import it.spootify.spootify.web.dto.StringUtils;

@Service
public class RiproduzioneServiceImpl implements RiproduzioneService{

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private RiproduzioneRepository riproduzioneRepository;
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private PlaylistRepository playlistRepository;
	@Autowired
	private UtenteRepository utenteRepository;
	
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
	@Transactional
	@Override
	public Riproduzione avviaRiproduzione(RiproduzioneProvaDTO example) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(example.getIdAlbum()) && StringUtils.isBlank(example.getIdPlaylist())) {
			System.out.println("non è stato selezionato nessun elemento per la riproduzione");
			return null;
		}
		Utente utente = utenteRepository.caricaUtenteConCodice(example.getCodice());
		String query = "select distinct r from Riproduzione r join fetch r.customer c "
													  + "left join fetch r.playlist p "
													  + "left join fetch r.album a "
													  + "left join fetch r.brano b "
													  + "where c.id = "+utente.getId()+" ";
		if(StringUtils.isNotBlank(example.getIdAlbum())) {
			query +=" and a.id = "+example.getIdAlbum()+" ";
		}
		if(StringUtils.isNotBlank(example.getIdPlaylist()) && StringUtils.isBlank(example.getIdAlbum())) {
			query +=" and p.id = "+example.getIdPlaylist()+" ";
		}
		
		System.out.println(query);
		List<Riproduzione> lista = entityManager.createQuery(query, Riproduzione.class).getResultList();
		//	CREO NUOVA RIPRODUZIONE 
		if(lista.isEmpty()) {
			System.out.println("riproduzione inesistente, creao una nuova");
			Riproduzione riproduzione = new Riproduzione();
			riproduzione.setCustomer(utente);
			if(StringUtils.isNotBlank(example.getIdAlbum())) {
				Album album = albumRepository.findByIdWithBrani(Long.parseLong(example.getIdAlbum()));
				riproduzione.setAlbum(album);
				riproduzione.setBrano(album.getBrani().get(0));
			}else {
				Playlist playlist = playlistRepository.findByIdWithBrani(Long.parseLong(example.getIdPlaylist()));
				riproduzione.setPlaylist(playlist);
				riproduzione.setBrano(playlist.getBrani().get(0));
			}
			riproduzioneRepository.save(riproduzione);
			return riproduzione;
		}
		
		// CARICO RIPRODUZIONE ESISTENTE
		Riproduzione riproduzione1 = lista.get(0);
		if(riproduzione1.getAlbum()!=null) {
			Album album1 = albumRepository.findByIdWithBrani(Long.parseLong(example.getIdAlbum()));
			riproduzione1.setAlbum(album1);
			if(riproduzione1.getBrano()==null) {
				riproduzione1.setBrano(album1.getBrani().get(0));
			}
		}else {
			Playlist playlist1 = playlistRepository.findByIdWithBrani(Long.parseLong(example.getIdPlaylist()));
			riproduzione1.setPlaylist(playlist1);
			if(riproduzione1.getBrano()==null) {
				riproduzione1.setBrano(playlist1.getBrani().get(0));
			}
		}
		return riproduzione1;
	}
	
	@Transactional
	@Override
	public Brano cambiaBrano(RiproduzioneProvaDTO input) {
		// TODO Auto-generated method stub
		Riproduzione riproduzione = riproduzioneRepository.findById(input.getId()).orElse(null);
		 //controllo index brano nell'album
		if(riproduzione.getAlbum()!=null) {
			Album album = albumRepository.findByIdWithBrani(riproduzione.getAlbum().getId());
			for(int i=0; i<album.getBrani().size(); i++) {
				System.out.println("id brano album: "+album.getBrani().get(i).getId()+"  , id brano riproduzione: "+riproduzione.getBrano().getId());
				if(album.getBrani().get(i).getId() == riproduzione.getBrano().getId()) {
					//brano precedente
					if(input.getBrano().equals("back")){
						if(i>0) {
							riproduzione.setBrano(album.getBrani().get(i-1));
							break;
						}else {
							riproduzione.setBrano(album.getBrani().get(album.getBrani().size()-1));
							break;
						}
					}// brano successivo
					else {
						if(i<album.getBrani().size()-1){
							riproduzione.setBrano(album.getBrani().get(i+1));
							break;
						}else {
							riproduzione.setBrano(album.getBrani().get(0));
							break;
						}
					}
				}
			}
		}// controllo index brano nella playlist
		else {
			Playlist playlist = playlistRepository.findByIdWithBrani(riproduzione.getPlaylist().getId());
			for(int i=0; i<playlist.getBrani().size(); i++) {
				if(playlist.getBrani().get(i).getId() == riproduzione.getBrano().getId()) {
					//brano precedente
					if(input.getBrano().equals("back")){
						if(i>0) {
							riproduzione.setBrano(playlist.getBrani().get(i-1));
							break;
						}else {
							riproduzione.setBrano(playlist.getBrani().get(playlist.getBrani().size()-1));
							break;
						}
					}// brano successivo
					else {
						if(i<playlist.getBrani().size()-1){
							riproduzione.setBrano(playlist.getBrani().get(i+1));
							break;
						}else {
							riproduzione.setBrano(playlist.getBrani().get(0));
							break;
						}
					}
				}
			}
		}
		riproduzioneRepository.save(riproduzione);
		return riproduzione.getBrano();
	}
	
	@Override
	public List<Riproduzione> cercaExample(Riproduzione example) {
		// TODO Auto-generated method stub
		return null;
	}

}
