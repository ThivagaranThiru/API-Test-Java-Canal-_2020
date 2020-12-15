package com.quest.canal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.quest.canal.model.Mouvement;
import com.quest.canal.repositories.MouvementRepository;

@Service
public class MouvementService implements IModelService<Mouvement> {
	
	@Autowired
	private MouvementRepository mouvementRepository;


	@Override
	public List<Mouvement> getList(Integer object, Integer limit) {
		// TODO Auto-generated method stub
		
		PageRequest page = PageRequest.of(object,limit);
		
		return mouvementRepository.findAllMouvement(page);
	}
	
	public List<Mouvement> findByAbonne(Integer id){
		
		return mouvementRepository.findByAbonne(id);
	}

	@Override
	public Mouvement getOneById(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<Mouvement> mouvement = mouvementRepository.findById(id);

		if(!mouvement.isPresent()) {
			return null;
		}
		
		return mouvement.get();

	}

	@Override
	public Mouvement create(Mouvement entity) {
		// TODO Auto-generated method stub
		
		mouvementRepository.save(entity);
		
		return entity;

	}

	@Override
	public Mouvement update(Integer id, Mouvement entity) {
		// TODO Auto-generated method stub

		if(getOneById(id) == null) {
			return null;
		}
		
		entity.setId(id);
		entity.setCreationDate(getOneById(id).getCreationDate());
		
		mouvementRepository.save(entity);
		
		return entity;

	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		
		try {
			
			if(getOneById(id) == null) {
				return false;
			}
			
			mouvementRepository.delete(getOneById(id));
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return false;
		}
		
		return true;
	}
}
