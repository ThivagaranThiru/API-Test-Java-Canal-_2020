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

import com.quest.canal.model.Abonne;
import com.quest.canal.model.Contrat;
import com.quest.canal.service.AbonneService;
import com.quest.canal.service.ContratService;

@CrossOrigin
@RestController
@RequestMapping("/contrat")
public class ContratController {
	
	@Autowired
	private ContratService contratService; 
	
	@Autowired
	private AbonneService abonneService;
	
	private List<Contrat> contratList = new ArrayList<Contrat>();
	
	@GetMapping()
	public ResponseEntity<List<Contrat>> getList(@SessionAttribute("newAbonne") Abonne abonne, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "100") Integer limit){
		
		if (abonne != null) {
			contratList = contratService.getList(page - 1, limit);
			return new ResponseEntity<List<Contrat>>(contratList, HttpStatus.OK);
		}

		return new ResponseEntity<List<Contrat>>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contrat> getbyID(@PathVariable("id") Integer id, @SessionAttribute("newAbonne") Abonne abonne) {
		
		Abonne abonneNew;
		
		if (abonne == null)
			return new ResponseEntity<Contrat>(HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId());

		Contrat contrat = contratService.getOneById(id);
		List<Contrat> contratAbonne = abonneNew.getContrats(); 

		if (contrat == null || contratAbonne == null)
			return new ResponseEntity<Contrat>(contrat,HttpStatus.NOT_FOUND);

		if (contratAbonne.contains(contrat))
			return new ResponseEntity<Contrat>(contrat, HttpStatus.OK);

		return new ResponseEntity<Contrat>(contrat,HttpStatus.NOT_FOUND);
	}
	

	@PostMapping()
	public ResponseEntity<Contrat> createContrat(@SessionAttribute("newAbonne") Abonne abonne) {
		Abonne newAbonne;
		if (abonne == null)
			return new ResponseEntity<Contrat>(new Contrat(), HttpStatus.UNAUTHORIZED);
		else newAbonne = abonneService.findByAbonne(abonne.getId());

		Contrat contratNew = contratService.create(new Contrat(newAbonne.getAdresse()));
		
		newAbonne.getContrats().add(contratNew);
		abonneService.update(newAbonne.getId(), newAbonne);
		
		return new ResponseEntity<Contrat>(contratNew, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contrat> updateContrat(@SessionAttribute("newAbonne") Abonne abonne, @PathVariable("id") Integer id, @RequestBody Contrat contrat) {

		Abonne abonneNew;
		
		if (abonne == null)
			return new ResponseEntity<>(contrat, HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId());

		Contrat contract = contratService.getOneById(id);
		List<Contrat> contratAbonne = abonneNew.getContrats();

		if (contract == null || contratAbonne == null)
			return new ResponseEntity<Contrat>(contrat, HttpStatus.NOT_FOUND);

		if (contratAbonne.contains(contract))
			return new ResponseEntity<Contrat>(contratService.update(id, contrat), HttpStatus.OK);

		return new ResponseEntity<Contrat>(HttpStatus.UNAUTHORIZED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Contrat> deleteContrat(@SessionAttribute("newAbonne") Abonne abonne, @PathVariable("id") Integer id) {
		
		Abonne abonneNew;
		
		if (abonne == null)
			return new ResponseEntity<Contrat>(HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId());
		
		Contrat contrat = contratService.getOneById(id);
		List<Contrat> contratAbonne = abonneNew.getContrats();
		
		if (contrat == null || contratAbonne == null)
			return new ResponseEntity<Contrat>(contrat, HttpStatus.NOT_FOUND);
		
		if (contratAbonne.contains(contrat)){
			
			Boolean sucess = contratService.delete(id);
			
			if (sucess)
				return new ResponseEntity<Contrat>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Contrat>(HttpStatus.UNAUTHORIZED);
	}

}
