package heig.metier.entite;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Electeur implements Serializable, IPersistable {
	private static final long serialVersionUID = 1L;
	

	@Id @GeneratedValue
	private Integer id;
	@Column(length=20)
	private String nom;
	@Column(length=20)
	private String prenom;
	private Date ddn;
	@Column(length=30)
	private String localite;
	@ManyToMany(mappedBy="electeurs")
	private List<Election> elections;
	
	public Electeur() {
		super();
		this.elections = new ArrayList<Election>();
	}

	public Electeur(Integer id, String nom, String prenom, Date ddn, String localite) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.ddn = ddn;
		this.localite = localite;
	}

	public Integer getId() {
		return id;
	}
	
	public String getIdSt(){
		return id.toString();
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

	public String getPrenom() {
		return prenom;
	}
	
	public String getNomComplet() {
		return nom + " "+prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDdn() {
		return ddn;
	}

	public void setDdn(Date ddn) {
		this.ddn = ddn;
	}

	public String getLocalite() {
		return localite;
	}

	public void setLocalite(String localite) {
		this.localite = localite;
	}

	public List<Election> getElections() {
		return elections;
	}

	public void setElections(List<Election> elections) {
		this.elections = elections;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Electeur [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ddn == null) ? 0 : ddn.hashCode());
		result = prime * result + ((elections == null) ? 0 : elections.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localite == null) ? 0 : localite.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		Electeur other = (Electeur) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}

}
