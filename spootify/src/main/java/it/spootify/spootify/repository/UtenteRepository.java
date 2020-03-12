package it.spootify.spootify.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.spootify.spootify.model.Utente;

public interface UtenteRepository extends CrudRepository< Utente , Long>,QueryByExampleExecutor < Utente >{
	
	@Query("select distinct u from Utente u left join fetch u.ruoli left join fetch u.listaPlaylist where u.id=?1")
	Utente findByIdEager(Long id);
	@Query("select distinct u from Utente u left join fetch u.sessione s where u.username=?1 and u.password=?2")
	Utente eseguiAccesso(String username, String password);
	@Query("select distinct u from Utente u left join fetch u.sessione s where u.id=?1")
	Utente caricaUtenteConSessione(Long id);
	@Query("select distinct u from Utente u join fetch u.sessione s left join fetch u.ruoli r where s.codice = ?1")
	Utente caricaUtenteConCodice(String codiceSessione);

}
