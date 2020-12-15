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
import com.quest.canal.service.AdresseService;
import com.quest.canal.service.ContratService;
import com.quest.canal.service.MouvementService;
import com.quest.canal.model.Abonne;
import com.quest.canal.model.Adresse;
import com.quest.canal.model.Contrat;
import com.quest.canal.model.Mouvement;

@CrossOrigin
@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AdresseService adresseService; 
	
	@Autowired
	private AbonneService abonneService;
	
	@Autowired
	private ContratService contratService;
	
	@Autowired
	private MouvementService mouvementService;
	
	private List<Adresse> adresseList = new ArrayList<Adresse>();
	
	
	@GetMapping()
	public ResponseEntity<List<Adresse>> getList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "100") Integer limit, @SessionAttribute("newAbonne") Abonne abonne){
		if (abonneService.findByAbonne(abonne.getId()) != null) {
			adresseList = adresseService.getList(page - 1, limit);
			return new ResponseEntity<List<Adresse>>(adresseList, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<Adresse>>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Adresse> getbyID(@PathVariable("id") Integer id, @SessionAttribute("newAbonne") Abonne abonne) {
		Abonne abonneNew;
		
		if (abonne.getId() == null)
			return new ResponseEntity<Adresse>(HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId());

		Adresse adresse = adresseService.getOneById(id);
		Adresse adresseUser= abonneNew.getAdresse();

		if (adresse == null || adresseUser == null)
			return new ResponseEntity<Adresse>(adresse,HttpStatus.NOT_FOUND);


		if (adresse.equals(adresseUser))
			return new ResponseEntity<Adresse>(adresse, HttpStatus.OK);

		return new ResponseEntity<Adresse>(adresse,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping()
	public ResponseEntity<Adresse> createAdresse(@RequestBody Adresse adresse, @SessionAttribute("newAbonne") Abonne abonne) {
		
		Abonne abonneNew = null;
		
		if (abonne == null) {
			return new ResponseEntity<Adresse>(adresse, HttpStatus.UNAUTHORIZED);
		}else {
			abonneNew = abonneService.findByAbonne(abonne.getId());
		}
			

		if (abonneNew != null) {
			Adresse adresseNew = adresseService.create(adresse);
			abonneNew.setAdresse(adresseNew);
			abonneService.update(abonneNew .getId(), abonneNew);
			
			return new ResponseEntity<Adresse>(adresseNew, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Adresse>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Adresse> updateAdresse(@PathVariable("id") Integer id, @RequestBody Adresse adresse, @SessionAttribute("newAbonne") Abonne abonne) {
		
		Abonne abonneNew = null;
		
		if (abonne == null) {
			return new ResponseEntity<Adresse>(adresse, HttpStatus.UNAUTHORIZED);
		}else {
			abonneNew = abonneService.findByAbonne(abonne.getId());
		}

		Adresse adress = adresseService.getOneById(id);
		Adresse adresseUser= abonneNew.getAdresse();
		String oldAdresse = adresseUser.toString();

		if (adress == null || adresseUser == null)
			return new ResponseEntity<Adresse>(adresse, HttpStatus.NOT_FOUND);

		if (adress.equals(adresseUser)) {
			Adresse newAdresse = adresseService.update(id, adresse);
			List<Contrat> contratByAdresse = contratService.getContratByAdresse(id);
			Mouvement mouvement = new Mouvement(abonneNew, contratByAdresse, oldAdresse, newAdresse.toString(),"MOUVEMENT_ADRESSE");
			mouvementService.create(mouvement);
			return new ResponseEntity<Adresse>(newAdresse, HttpStatus.OK);
		}

		return new ResponseEntity<Adresse>(HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Adresse> deleteAdresse(@PathVariable("id") Integer id, @SessionAttribute("newAbonne") Abonne abonne) {
		
		Abonne abonneNew;
		
		if (abonne == null)
			return new ResponseEntity<Adresse>(HttpStatus.UNAUTHORIZED);
		else abonneNew = abonneService.findByAbonne(abonne.getId());

		Adresse adress = adresseService.getOneById(id);
		Adresse adresseUser= abonneNew.getAdresse();
		
		if (adress == null || adresseUser == null)
			return new ResponseEntity<Adresse>(adress, HttpStatus.NOT_FOUND);

		if (adress.equals(adresseUser)){
			
			Boolean sucess = adresseService.delete(id);
			
			if (sucess)
				return new ResponseEntity<Adresse>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Adresse>(HttpStatus.UNAUTHORIZED);
	}
}
