package com.quest.canal.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.quest.canal.model.Abonne;

@Repository
public interface AbonneRepository extends PagingAndSortingRepository< Abonne , Integer>{
	
	@Query("SELECT a FROM Abonne a WHERE a.nom = :nom")
	public Abonne findByUsername(String nom);
	
	@Query("SELECT a FROM Abonne a ORDER BY a.id ASC")
	public List<Abonne> getListAbonnePage(Pageable page);
	
	@Query("SELECT a FROM Abonne a WHERE a.id = :id")
	public Abonne findByAbonne(Integer id);
}
