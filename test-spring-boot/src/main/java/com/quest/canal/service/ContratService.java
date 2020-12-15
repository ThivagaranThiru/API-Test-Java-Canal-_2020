package com.quest.canal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.quest.canal.model.Contrat;
import com.quest.canal.repositories.ContratRepository;

@Service
public class ContratService implements IModelService<Contrat> {
	
	@Autowired
	private ContratRepository contratRepository;


	@Override
	public List<Contrat> getList(Integer object, Integer limit) {
		// TODO Auto-generated method stub
		
		PageRequest page = PageRequest.of(object,limit);
		
		return contratRepository.findAllByPageContrat(page);

	}
	
	public List<Contrat> getContratByAdresse(Integer id) {
		// TODO Auto-generated method stub
		
		return contratRepository.findByAdresse(id);

	}

	@Override
	public Contrat getOneById(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<Contrat> contrat = contratRepository.findById(id);

		if(!contrat.isPresent()) {
			return null;
		}
		
		return contrat.get();

	}

	@Override
	public Contrat create(Contrat entity) {
		// TODO Auto-generated method stub
		
		contratRepository.save(entity);
		
		return entity;

	}

	@Override
	public Contrat update(Integer id, Contrat entity) {
		// TODO Auto-generated method stub
		
		if(getOneById(id) == null) {
			return null;
		}
		
		entity.setId(id);
		entity.setCreationDate(getOneById(id).getCreationDate());
		entity.setUpdatedDate(getOneById(id).getUpdatedDate());
		
		contratRepository.save(entity);
		
		return entity;

	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		
		try {
			
			if(getOneById(id) == null) {
				return false;
			}
			
			contratRepository.delete(getOneById(id));
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return false;
		}
		
		return true;
	}
}
