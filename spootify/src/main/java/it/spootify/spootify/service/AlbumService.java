package it.spootify.spootify.service;

import it.spootify.spootify.model.Album;

public interface AlbumService extends IBaseService<Album>{

	public Album caricaConIdEager(Long id);

}
