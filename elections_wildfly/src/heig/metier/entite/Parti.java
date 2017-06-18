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
import javax.persistence.OneToMany;

@Entity
public class Parti implements Serializable, IPersistable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Integer id;
	@Column(length=20)
	private String nom;
	private Date ddf;
	@Column(length=30)
	private String localite;
	@OneToMany(mappedBy="parti", cascade=CascadeType.ALL, orphanRemoval=false)
	private List<Candidat> candidats;
	
	public Parti() {
		super();
	}
	
	public Parti(Integer id, String nom, Date ddf, String localite){
		this.id = id;
		this.nom = nom;
		this.ddf = ddf;
		this.localite = localite;
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

	public Date getDdf() {
		return ddf;
	}

	public void setDdf(Date ddf) {
		this.ddf = ddf;
	}

	public String getLocalite() {
		return localite;
	}

	public void setLocalite(String localite) {
		this.localite = localite;
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

	public void addCandidat(Candidat c) {
		c.setParti(this);
		getCandidats().add(c);
	}
	
	public void removeCandidat(Candidat c) {
		getCandidats().remove(c);
		c.setParti(null);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ddf == null) ? 0 : ddf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localite == null) ? 0 : localite.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Parti other = (Parti) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Parti [id=" + id + ", nom=" + nom + ", ddf=" + ddf + ", localite=" + localite + "]";
	}
	
}
