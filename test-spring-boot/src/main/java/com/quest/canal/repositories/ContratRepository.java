package com.quest.canal.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.quest.canal.model.Contrat;

@Repository
public interface ContratRepository extends PagingAndSortingRepository<Contrat,Integer> {
	
	@Query("SELECT c FROM Contrat c ORDER BY c.id ASC")
	public List<Contrat> findAllByPageContrat(Pageable page);
	
	@Query("SELECT c FROM Contrat c WHERE c.adresse.id = :id")
	public List<Contrat> findByAdresse(Integer id);
	
}
