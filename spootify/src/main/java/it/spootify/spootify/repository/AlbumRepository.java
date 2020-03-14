package it.spootify.spootify.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.spootify.model.Album;


public interface AlbumRepository extends CrudRepository< Album , Long>,QueryByExampleExecutor < Album > {
	@Query("select distinct a from Album a left join fetch a.brani b left join fetch a.artista where a.id=?1")
	Album findByIdEager(Long id);
	@Query("select distinct a from Album a left join fetch a.brani b where a.id=?1")
	Album findByIdWithBrani(long parseLong);

}
