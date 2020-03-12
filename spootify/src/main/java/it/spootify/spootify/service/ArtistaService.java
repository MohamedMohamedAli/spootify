package it.spootify.spootify.service;

import it.spootify.spootify.model.Artista;

public interface ArtistaService extends IBaseService<Artista>{

	public Artista caricaConIdEager(Long id);

}
