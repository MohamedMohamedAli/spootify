package it.spootify.spootify.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.spootify.model.Sessione;

public interface SessioneRepository extends CrudRepository< Sessione , Long>,QueryByExampleExecutor < Sessione >{

}
