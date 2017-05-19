package heig.metier;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Parti implements Serializable {

	@Id @GeneratedValue
	private int id;
	@Column(length=20)
	private String nom;
	@Column(length=30)
	private Date ddf;
	@Column(length=30)
	private String localite;
	
	public Parti() {
		super();
		id = 0;
	}
	
	public Parti(int id, String nom, Date ddf, String localite){
		this.id = id;
		this.nom = nom;
		this.ddf = ddf;
		this.localite = localite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	
}
