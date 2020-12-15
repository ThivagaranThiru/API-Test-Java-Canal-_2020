package com.quest.canal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.quest.canal.model.Abonne;
import com.quest.canal.repositories.AbonneRepository;

@RestController
@CrossOrigin
@SessionAttributes("newAbonne")
public class AuthenticationController {
	
	@Autowired
	private AbonneRepository abonneRepository;
	
	Abonne newAbonne;
	
	@PostMapping("/register")
	public ResponseEntity<Abonne> register (@RequestBody Abonne abonne, ModelMap model) {		
		
		if(abonneRepository.findByUsername(abonne.getNom()) != null) {
			return new ResponseEntity<Abonne>(HttpStatus.CONFLICT);
		}
		
		abonne = abonneRepository.save(abonne);
		
		model.addAttribute("newAbonne", abonne);
		
		return new ResponseEntity<Abonne>(newAbonne,HttpStatus.CREATED);
	}
}
