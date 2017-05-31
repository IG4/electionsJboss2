package heig.metier.entite;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Election implements Serializable, IPersistable {
	private static final long serialVersionUID = 1L;
	

@Id @GeneratedValue
	private Integer id;
	@Column(length=20)
	private String nom;
	@Column(length=20)
	private String code;
	@Column()
	private Date debut;
	@Column()
	private Date fin;
	
	private List<Electeur> electeurs;
	
	private List<Candidat> candidats;
	

	public Election() {
		super();
	}

	public Election(Integer id, String nom, String code, Date debut, Date fin) {
		super();
		this.id = id;
		this.nom = nom;
		this.code = code;
		this.debut = debut;
		this.fin = fin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public List<Electeur> getElecteurs() {
		return electeurs;
	}

	public void setElecteurs(List<Electeur> electeurs) {
		this.electeurs = electeurs;
	}

	public List<Candidat> getCandidats() {
		return candidats;
	}

	public void setCandidats(List<Candidat> candidats) {
		this.candidats = candidats;
	}

}
