package heig.metier.entite;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Vote implements Serializable, IPersistable {
	private static final long serialVersionUID = 1L;
	

@Id @GeneratedValue
	private Integer id;
	@Column(length=20)
	private Candidat candidat;
	@Column(length=20)
	private Electeur electeur;
	@ManyToMany(mappedBy="votes")
	private List<Election> elections;
	
	public Vote() {
		super();
	}

	public Vote(Integer id, Candidat candidat, Electeur electeur) {
		super();
		this.id = id;
		this.candidat = candidat;
		this.electeur = electeur;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public Electeur getElecteur() {
		return electeur;
	}

	public void setElecteur(Electeur electeur) {
		this.electeur = electeur;
	}

	public List<Election> getElections() {
		return elections;
	}

	public void setElections(List<Election> elections) {
		this.elections = elections;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidat == null) ? 0 : candidat.hashCode());
		result = prime * result + ((electeur == null) ? 0 : electeur.hashCode());
		result = prime * result + ((elections == null) ? 0 : elections.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}

}
