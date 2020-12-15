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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.quest.canal.service.AbonneService;
import com.quest.canal.service.MouvementService;
import com.quest.canal.model.Abonne;
import com.quest.canal.model.Mouvement;

@CrossOrigin
@RestController
@RequestMapping("/mouvement")
public class MouvementController {
	
	@Autowired
	private MouvementService mouvementService; 
	
	@Autowired
	private AbonneService abonneService;
	
	private List<Mouvement> mouvementList = new ArrayList<Mouvement>();
	

	@GetMapping()
	public ResponseEntity<List<Mouvement>> getList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "100") Integer limit, @SessionAttribute("newAbonne") Abonne abonne){
		if (abonne != null) {
			mouvementList = mouvementService.getList(page - 1, limit);
			return new ResponseEntity<List<Mouvement>>(mouvementList, HttpStatus.OK);
		}

		return new ResponseEntity<List<Mouvement>>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Mouvement> getbyID(@SessionAttribute("newAbonne") Abonne abonne, @PathVariable("id") Integer id) {
		
		Abonne abonneNew;
		
		if (abonne == null)
			return new ResponseEntity<Mouvement>(HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId());

		Mouvement mouvement = mouvementService.getOneById(id);
		List<Mouvement> mouvementAbonne = mouvementService.findByAbonne(abonneNew.getId());

		if (mouvement == null || mouvementAbonne == null)
			return new ResponseEntity<Mouvement>(mouvement,HttpStatus.NOT_FOUND);

		if (mouvementAbonne.contains(mouvement))
			return new ResponseEntity<Mouvement>(mouvement, HttpStatus.OK);

		return new ResponseEntity<Mouvement>(mouvement,HttpStatus.NOT_FOUND);
	
	}
	
	@PostMapping()
	public ResponseEntity<Mouvement> createMouvement(@SessionAttribute("newAbonne") Abonne abonne, @RequestBody Mouvement mouvement) {
		Abonne abonneNew;
		
		if (abonne == null)
			return new ResponseEntity<Mouvement>(mouvement, HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId());
			 mouvement.setAbonne(abonneNew);
			 Mouvement mvtNew = mouvementService.create(mouvement);
			 return new ResponseEntity<Mouvement>(mvtNew, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Mouvement> updateMouvement(@SessionAttribute("newAbonne") Abonne abonne, @PathVariable("id") Integer id, @RequestBody Mouvement mouvement) {
		Abonne abonneNew;
		
		if (abonne == null)
			return new ResponseEntity<Mouvement>(mouvement, HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId());

		Mouvement mvt = mouvementService.getOneById(id);
		List<Mouvement> mouvementAbonne  = mouvementService.findByAbonne(abonneNew.getId());

		if (mvt == null || mouvementAbonne == null)
			return new ResponseEntity<Mouvement>(mouvement, HttpStatus.NOT_FOUND);


		if (mouvementAbonne.contains(mvt))
			return new ResponseEntity<Mouvement>(mouvementService.update(id, mouvement), HttpStatus.OK);

		return new ResponseEntity<Mouvement>(HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Mouvement> deleteMouvement(@SessionAttribute("newAbonne") Abonne abonne, @PathVariable("id") Integer id) {
		Abonne abonneNew;
		
		if (abonne == null)
			return new ResponseEntity<Mouvement>(HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId());
		
		Mouvement mvt = mouvementService.getOneById(id);
		List<Mouvement> mouvementAbonne = mouvementService.findByAbonne(abonneNew.getId());
		
		if (mvt == null || mouvementAbonne == null)
			return new ResponseEntity<Mouvement>(mvt, HttpStatus.NOT_FOUND);

		
		if (mouvementAbonne.contains(mvt)){
			
			Boolean sucess = mouvementService.delete(id);
			
			if (sucess)
				return new ResponseEntity<Mouvement>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Mouvement>(HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/MvtByAbonne")
	public ResponseEntity<List<Mouvement>> getListMouvement(@SessionAttribute("newAbonne") Abonne abonne){
		if (abonne != null) {
			return new ResponseEntity<List<Mouvement>>(mouvementService.findByAbonne(abonne.getId()), HttpStatus.OK);
		}

		return new ResponseEntity<List<Mouvement>>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
	}
}
