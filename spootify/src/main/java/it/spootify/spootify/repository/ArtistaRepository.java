package it.spootify.spootify.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.spootify.model.Artista;


public interface ArtistaRepository extends CrudRepository< Artista , Long>,QueryByExampleExecutor < Artista > {
	@Query("select distinct a from Artista a left join fetch a.listaAlbum l where a.id=?1")
	Artista findByIdEager(Long id);

}
