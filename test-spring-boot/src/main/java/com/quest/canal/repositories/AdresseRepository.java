package com.quest.canal.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.quest.canal.model.Adresse;

@Repository
public interface AdresseRepository extends PagingAndSortingRepository<Adresse, Integer>{
	
	@Query("SELECT a FROM Adresse a ORDER BY a.id ASC")
	public List<Adresse> findAllByPageAdresse(Pageable page);
	

}
