package com.quest.canal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.quest.canal.model.Abonne;
import com.quest.canal.service.AbonneService;

@CrossOrigin
@RestController
@RequestMapping("/abonne")
public class AbonneController {
	
	@Autowired
	private AbonneService abonneService;
	
	private List<Abonne> abonneList = new ArrayList<Abonne>();
	
	@GetMapping()
	public ResponseEntity<List<Abonne>> getList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "100") Integer limit, @SessionAttribute("newAbonne") Abonne abonne){
		
		if(abonne == null) {
			return new ResponseEntity<List<Abonne>>(abonneList, HttpStatus.UNAUTHORIZED);
		}

		abonneList = abonneService.getList(page - 1, limit);
		return new ResponseEntity<List<Abonne>>(abonneList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Abonne> getOneById(@SessionAttribute("newAbonne") Abonne abonne, @PathVariable("id") Integer id){
		if (abonneService.findByAbonne(abonne.getId())  == null)
			return new ResponseEntity<Abonne>(HttpStatus.UNAUTHORIZED);

		Abonne abonneId = abonneService.getOneById(id);
		
		if (abonneId != null) {
			return new ResponseEntity<Abonne>(abonneId, HttpStatus.OK);
		}
		
		return new ResponseEntity<Abonne>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Abonne> updateUser(@SessionAttribute("newAbonne") Abonne abonne, @PathVariable("id") Integer id, @RequestBody Abonne abonnePut) {
		Abonne abonneNew;
		
		if (abonne  == null)
			return new ResponseEntity<Abonne>(abonne, HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId()); 

		if (abonneNew.getId().equals(id)) {
			abonneNew.setPrenom(abonnePut.getPrenom());
			abonneNew.setNom(abonnePut.getNom());
			Abonne updatedAbonne = abonneService.update(id, abonneNew);
			if (updatedAbonne == null)
				return new ResponseEntity<Abonne>(abonne, HttpStatus.NOT_FOUND);
			return new ResponseEntity<Abonne>(updatedAbonne, HttpStatus.OK);
		}
		
		return new ResponseEntity<Abonne>(abonne, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Abonne> deleteUser(@SessionAttribute("newAbonne") Abonne abonne, @PathVariable("id") Integer id){
		
		Abonne abonneNew;
		
		if (abonne == null)
			return new ResponseEntity<Abonne>(HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId());
		
		if (abonneNew.getId().equals(id)) {
			Boolean success = abonneService.delete(id);
			
			if (success)
				return new ResponseEntity<Abonne>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Abonne>(HttpStatus.UNAUTHORIZED);
	}
}
