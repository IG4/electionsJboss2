package heig.metier.entite;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
	
	@ManyToMany
	private List<Electeur> electeurs;
	
	@ManyToMany
	private List<Candidat> candidats;
	
	@OneToMany(mappedBy="election", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Vote> votes;
	

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
		if (electeurs == null) {
			electeurs = new ArrayList<Electeur>();
		}
		return electeurs;
	}

	public void setElecteurs(List<Electeur> electeurs) {
		this.electeurs = electeurs;
	}

	public List<Candidat> getCandidats() {
		if (candidats == null) {
			candidats = new ArrayList<Candidat>();
		}
		return candidats;
	}

	public void setCandidats(List<Candidat> candidats) {
		this.candidats = candidats;
	}

	public List<Vote> getVotes() {
		if (votes == null) {
			votes = new ArrayList<Vote>();
		}
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public Electeur getElecteur(Integer id) {
		if (id != null) {
			for (Electeur el : getElecteurs()) {
				if (id.equals(el.getId())) {
					return el;
				}
			}
		}
		return null;
	}
	
	public Candidat getCandidat(Integer id) {
		if (id != null) {
			for (Candidat cd : getCandidats()) {
				if (id.equals(cd.getId())) {
					return cd;
				}
			}
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((candidats == null) ? 0 : candidats.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((debut == null) ? 0 : debut.hashCode());
		result = prime * result + ((electeurs == null) ? 0 : electeurs.hashCode());
		result = prime * result + ((fin == null) ? 0 : fin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((votes == null) ? 0 : votes.hashCode());
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
		Election other = (Election) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Election [id=" + id + ", nom=" + nom + ", code=" + code + ", debut=" + debut + ", fin=" + fin
				+ ", electeurs=" + electeurs + ", candidats=" + candidats + ", votes=" + votes + "]";
	}

	
}
