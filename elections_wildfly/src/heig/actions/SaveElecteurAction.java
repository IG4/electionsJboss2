package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Electeur;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@Results({ @Result(name = "success", type = "chain", location = "list-electeurs"),
		@Result(name = "input", location = "page.edit", type = "tiles") })
@SuppressWarnings("serial")
public class SaveElecteurAction extends ActionSupport {

	private Electeur electeur;

	public String execute() {
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
			elections.saveElecteur(electeur);
		} catch (PersistException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public Electeur getElecteur() {
		return electeur;
	}

	public void setElecteur(Electeur electeur) {
		this.electeur = electeur;
	}

	@Override
	public void validate() {
		if ((electeur.getPrenom() == null) || (electeur.getPrenom().length() < 3)) {
			addActionError(getText("prenom.tropcourt"));
		}
		if ((electeur.getNom() == null) || (electeur.getNom().length() < 3)) {
			addActionError(getText("nom.tropcourt"));
		}
	}

}
