package heig.metier.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Vote implements Serializable, IPersistable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Integer id;
	@OneToOne
	@JoinColumn(name="candidat_id")
	private Candidat candidat;
	@OneToOne
	@JoinColumn(name="electeur_id")
	private Electeur electeur;
	@ManyToOne
	@JoinColumn(name="election_id")
	private Election election;
	
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

	public Election getElection() {
		if (election == null) {
			election = new Election();
		}
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidat == null) ? 0 : candidat.hashCode());
		result = prime * result + ((electeur == null) ? 0 : electeur.hashCode());
		result = prime * result + ((election == null) ? 0 : election.hashCode());
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
