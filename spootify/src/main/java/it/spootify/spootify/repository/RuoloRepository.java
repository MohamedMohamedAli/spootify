package it.spootify.spootify.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.spootify.model.Ruolo;

public interface RuoloRepository extends CrudRepository< Ruolo , Long>,QueryByExampleExecutor < Ruolo >{

}
