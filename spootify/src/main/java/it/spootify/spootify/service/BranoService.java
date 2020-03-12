package it.spootify.spootify.service;

import it.spootify.spootify.model.Brano;

public interface BranoService extends IBaseService<Brano>{

	public Brano caricaConIdEager(Long id);

}
