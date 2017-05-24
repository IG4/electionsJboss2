package heig.metier.entite;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Candidat implements Serializable, IPersistable {
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
	@Column(length=30)
	private String parti;

	
	public Candidat() {
		super();
		id = 0;
	}
	
	public Candidat(Integer id, String nom, String prenom, Date ddn, String localite, String parti) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.ddn = ddn;
		this.localite = localite;
		this.parti = parti;
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

	public String getParti() {
		return parti;
	}

	public void setParti(String parti) {
		this.parti = parti;
	}

	
}
