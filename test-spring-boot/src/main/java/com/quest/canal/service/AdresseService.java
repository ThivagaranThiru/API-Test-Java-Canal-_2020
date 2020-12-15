package com.quest.canal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.quest.canal.model.Adresse;
import com.quest.canal.repositories.AdresseRepository;

@Service
public class AdresseService implements IModelService<Adresse> {
	
	@Autowired
	private AdresseRepository adresseRepository;

	@Override
	public List<Adresse> getList(Integer object, Integer limit) {
		// TODO Auto-generated method stub
		

		PageRequest page = PageRequest.of(object,limit);
		
		return adresseRepository.findAllByPageAdresse(page);

	}

	@Override
	public Adresse getOneById(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<Adresse> adresse = adresseRepository.findById(id);

		if(!adresse.isPresent()) {
			return null;
		}
		
		return adresse.get();

	}

	@Override
	public Adresse create(Adresse entity) {
		// TODO Auto-generated method stub
		
		adresseRepository.save(entity);
		
		return entity;

	}

	@Override
	public Adresse update(Integer id, Adresse entity) {
		// TODO Auto-generated method stub
		
		if(getOneById(id) == null) {
			return null;
		}
		
		entity.setId(id);
		entity.setCreationDate(getOneById(id).getCreationDate());
		entity.setUpdatedDate(getOneById(id).getUpdatedDate());
		
		adresseRepository.save(entity);
		
		return entity;

	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		
		try {
			
			if(getOneById(id) == null) {
				return false;
			}
			
			adresseRepository.delete(getOneById(id));
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return false;
		}
		
		return true;
	}
	
}
