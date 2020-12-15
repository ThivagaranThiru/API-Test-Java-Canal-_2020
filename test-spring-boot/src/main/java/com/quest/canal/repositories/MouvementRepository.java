package com.quest.canal.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.quest.canal.model.Mouvement;

@Repository
public interface MouvementRepository extends PagingAndSortingRepository<Mouvement,Integer> {
	
	@Query("SELECT m FROM Mouvement m ORDER BY m.id ASC")
	public List<Mouvement> findAllMouvement(Pageable page);
	
	@Query("SELECT m FROM Mouvement m WHERE m.abonne.id = :id")
	public List<Mouvement> findByAbonne(Integer id);
	
}
