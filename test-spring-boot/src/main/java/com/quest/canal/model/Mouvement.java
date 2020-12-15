package com.quest.canal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "mouvement")
public class Mouvement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "INT(11)")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "abonne", nullable = false)
	private Abonne abonne;
	
	@ManyToMany
	@JoinColumn(name = "contrat", nullable = false)
	private List<Contrat> contrats = new ArrayList<>();
	
	@Column(name = "oldValue",nullable = false)
	private String oldValue;
	
	@Column(name = "newValue",nullable = false)
	private String newValue;
	
	@Column(name = "type",nullable = false, columnDefinition = "VARCHAR(100)")
	private String type;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationDate", columnDefinition = "TIMESTAMP", nullable = false,  updatable = false)
	@CreationTimestamp
	private Date creationDate;
	
	public Mouvement() {};
	
	public Mouvement(Integer id, Abonne abonne, List<Contrat> contrats, String oldValue, String newValue, String type, Date creationDate) {
		this.id = id;
		this.abonne = abonne;
		this.contrats = contrats;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.type = type;
		this.creationDate = creationDate;
	}
	
	public Mouvement(Abonne abonne, List<Contrat> contrats, String oldValue, String newValue, String type, Date creationDate) {
		this.abonne = abonne;
		this.contrats = contrats;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.type = type;
		this.creationDate = creationDate;
	}
	
	public Mouvement(Abonne abonne, List<Contrat> contrats, String oldValue, String newValue, String type) {
		this.abonne = abonne;
		this.contrats = contrats;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Abonne getAbonne() {
		return abonne;
	}

	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}

	public List<Contrat> getContrats() {
		return contrats;
	}

	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public String toString() {
		String toString = String.format("\n[id=%s, abonne=%s, contrats=%s, oldValue=%s, newValue=%s, type=%s, creationDate=%s", id, abonne.toString(),
				contrats.stream()
			      .map(n -> String.valueOf(n))
			      .collect(Collectors.joining("-", "{", "}")), oldValue, newValue, type, creationDate);
				
		return toString;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof Mouvement)) {
			return false;
		}
		
		Mouvement other = (Mouvement) object;
		
		if((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		
		return true;
	}
}
