package it.spootify.spootify.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.spootify.model.Riproduzione;

public interface RiproduzioneRepository extends CrudRepository< Riproduzione , Long>,QueryByExampleExecutor < Riproduzione >{

}
