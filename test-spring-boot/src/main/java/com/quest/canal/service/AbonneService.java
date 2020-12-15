package com.quest.canal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.quest.canal.model.Abonne;
import com.quest.canal.repositories.AbonneRepository;

@Service
public class AbonneService implements IModelService<Abonne> {
	

	@Autowired
	private AbonneRepository abonneRepository;
	

	@Override
	public List<Abonne> getList(Integer object, Integer limit) {
		// TODO Auto-generated method stub
		
		PageRequest page = PageRequest.of(object, limit);
		
		return abonneRepository.getListAbonnePage(page);
	}

	@Override
	public Abonne getOneById(Integer id) {
		
		Optional<Abonne> abonne = abonneRepository.findById(id);
		
		if (abonne.isPresent()) {
			return abonne.get();
		}
		
		return null;
	}

	@Override
	public Abonne create(Abonne entity) {
		// TODO Auto-generated method stub

		abonneRepository.save(entity);
		
		return entity;

	}

	@Override
	public Abonne update(Integer id, Abonne entity) {
		// TODO Auto-generated method stub
		
		if (getOneById(id) == null) {
			return null;
		}
		
		entity.setId(id);
		entity.setCreationDate(getOneById(id).getCreationDate());
		entity.setUpdatedDate(getOneById(id).getUpdatedDate());
		
		abonneRepository.save(entity);
		
		return entity;

	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		
		try {
			
			if(getOneById(id) == null) {
				return false;
			}
			
			abonneRepository.delete(getOneById(id));
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return false;
		}
		
		return true;

	}
	
	public Abonne findByAbonne(Integer id) {
		
		return abonneRepository.findByAbonne(id);
	}

}