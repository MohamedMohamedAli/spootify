package it.spootify.spootify.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.spootify.model.Brano;

public interface BranoRepository extends CrudRepository< Brano , Long>,QueryByExampleExecutor < Brano >{
	
	@Query("select distinct b from Brano b left join fetch b.album where b.id=?1")
	Brano findByIdEager(Long id);

}
