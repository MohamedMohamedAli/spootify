package it.spootify.spootify.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.spootify.model.Playlist;

public interface PlaylistRepository extends CrudRepository< Playlist , Long>,QueryByExampleExecutor < Playlist >{

	@Query("select distinct p from Playlist p left join fetch p.creatore c left join fetch p.brani b  where p.id=?1")
	Playlist findByIdEager(Long id);
	@Query("select distinct p from Playlist p left join fetch p.riproduzione r where p.id=?1")
	Playlist findByIdRiproduzione(Long id);
	@Query("select distinct p from Playlist p left join fetch p.brani where p.id=?1")
	Playlist findByIdWithBrani(Long id);

}
