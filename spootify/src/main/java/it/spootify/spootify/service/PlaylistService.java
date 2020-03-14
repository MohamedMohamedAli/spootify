package it.spootify.spootify.service;

import it.spootify.spootify.model.Playlist;

public interface PlaylistService extends IBaseService<Playlist>{

	public Playlist caricaConIdEager(Long id);

	public void crea(Playlist playlist, String codice);

}
