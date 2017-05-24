package heig.metier.entite;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Parti implements Serializable, IPersistable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Integer id;
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

	
}
