package heig.metier.entite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vote implements Serializable, IPersistable {
	private static final long serialVersionUID = 1L;
	

@Id @GeneratedValue
	private Integer id;
	@Column(length=20)
	private Candidat candidat;
	@Column(length=20)
	private Electeur electeur;

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

}
