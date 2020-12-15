package com.quest.canal.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "contrat")
public class Contrat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idContrat", columnDefinition = "INT(11)")
	private Integer idContrat;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adresse", nullable = false)
	private Adresse adresse;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creationDate", columnDefinition = "TIMESTAMP", nullable = false,  updatable = false)
	@CreationTimestamp
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedDate", columnDefinition = "TIMESTAMP",nullable = false)
	@UpdateTimestamp 
	private Date updatedDate;
	
	public Contrat() {};
	
	public Contrat(Integer id, Adresse adresse, Date creationDate, Date updateDate) {
		this.idContrat = id;
		this.adresse = adresse;
		this.creationDate = creationDate;
		this.updatedDate = updateDate;
	}
	
	public Contrat( Adresse adresse, Date creationDate, Date updateDate) {
		this.adresse = adresse;
		this.creationDate = creationDate;
		this.updatedDate = updateDate;
	}
	
	public Contrat(Adresse adresse) {
		this.adresse = adresse;
	}

	public Integer getId() {
		return this.idContrat;
	}

	public void setId(Integer id) {
		this.idContrat = id;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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
	
	@Override
	public String toString() {
		String toString = String.format("\n[id=%s, adresse=%s, creationDate=%s, updateUser=%s ", idContrat, adresse.toString(), creationDate, updatedDate);
				
		return toString;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idContrat != null ? idContrat.hashCode() : 0);
		return hash;
	}
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof Contrat)) {
			return false;
		}
		
		Contrat other = (Contrat) object;
		
		if((this.idContrat == null && other.idContrat != null) || (this.idContrat != null && !this.idContrat.equals(other.idContrat))) {
			return false;
		}
		
		return true;
	}
	
}
