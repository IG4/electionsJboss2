package heig.metier.entite;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Electeur implements Serializable, IPersistable {
	private static final long serialVersionUID = 1L;
	

@Id @GeneratedValue
	private Integer id;
	@Column(length=20)
	private String nom;
	@Column(length=20)
	private String prenom;
	@Column(length=30)
	private Date ddn;
	@Column(length=30)
	private String localite;

	public Electeur() {
		super();
		id = 0;
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

}
