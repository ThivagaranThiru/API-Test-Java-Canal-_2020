package com.quest.canal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "abonne")
public class Abonne{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idAbonne", columnDefinition = "INT(11)")
	private Integer idAbonne;

	@Column(name = "nom",nullable = true, columnDefinition = "VARCHAR(100)")
	private String nom;
	
	@Column(name = "prenom",nullable = true, columnDefinition = "VARCHAR(100)")
	private String prenom;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adresse", nullable = true)
	private Adresse adresse;

	 @OneToMany
	 @JoinTable( name = "T_Contrats_Abonnes_Associations",
	             joinColumns = @JoinColumn( name = "idAbonne" ),
	             inverseJoinColumns = @JoinColumn( name = "idContrat" ) )
    private List<Contrat> contrats = new ArrayList<>();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationDate", columnDefinition = "TIMESTAMP", nullable = true,  updatable = false)
	@CreationTimestamp
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedDate", columnDefinition = "TIMESTAMP",nullable = true)
	@UpdateTimestamp 
	private Date updatedDate;

	
	public Abonne() {};
	
	public Abonne(Integer id, String nom, String prenom, Adresse adresse, List<Contrat> contrats, Date add, Date modif) {
		this.idAbonne = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.contrats = contrats;
		this.creationDate = add;
		this.updatedDate = modif;
	}
	
	public Abonne(String nom, String prenom, Adresse adresse, List<Contrat> contrats, Date add, Date modif) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.contrats = contrats;
		this.creationDate = add;
		this.updatedDate = modif;
	}
	
	public Abonne(Integer id, String nom, String prenom, Adresse adresse, List<Contrat> contrats) {
		this.idAbonne = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.contrats = contrats;
	}
	
	public Abonne(String nom, String prenom, Adresse adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}
	
	public Abonne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Integer getId() {
		return idAbonne;
	}

	public void setId(Integer id) {
		this.idAbonne = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Contrat> getContrats() {
		return contrats;
	}

	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	@Override
	public String toString() {
		String toString = String.format("\n[id=%s, nom=%s, prenom=%s, adresse=%s, contrat=%s, creationDate=%s, updateUser=%s ", idAbonne, nom, prenom, (adresse != null) ? adresse.toString() : "None", 
			  (contrats != null) ? 
				contrats.stream()
			      .map(n -> String.valueOf(n))
			      .collect(Collectors.joining("-", "{", "}")) : "None",
			  creationDate, updatedDate);
				
		return toString;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idAbonne != null ? idAbonne.hashCode() : 0);
		return hash;
	}
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof Abonne)) {
			return false;
		}
		
		Abonne other = (Abonne) object;
		
		if((this.idAbonne == null && other.idAbonne != null) || (this.idAbonne != null && !this.idAbonne.equals(other.idAbonne))) {
			return false;
		}
		
		return true;
	}
}
