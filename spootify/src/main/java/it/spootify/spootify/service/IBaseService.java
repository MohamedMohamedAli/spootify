package it.spootify.spootify.service;

import java.util.List;

public interface IBaseService<T> {
	public List<T> listAll();
	public T caricaConId(Long id);
	public void inserisci(T input);
	public void aggiorna(T input);
	public void elimina(Long id);
	public List<T> cercaExample(T example);
}
