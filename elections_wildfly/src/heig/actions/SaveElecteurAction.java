package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import heig.entite.Electeur;
import heig.exceptions.PersistException;
import heig.session.IElections;

@Results({ @Result(name = "success", type = "chain", location = "list-electeurs"),
		@Result(name = "input", location = "page.edit.electeurs", type = "tiles") })
@SuppressWarnings("serial")
public class SaveElecteurAction extends ActionSupport {

	private Electeur electeur;

	public String execute() {
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
			elections.save(electeur);
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
		if ((electeur.getLocalite() == null) || (electeur.getLocalite().length() < 3)){
			addActionError(getText("localite.vide"));
		}
		
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
			if (!elections.checkDate(electeur.getDdn())) {
				addActionError(getText("date.formatincorrect"));
			}
		} catch (NamingException e1) {
			addActionError("Impossible de valider la date");
		}
	}

}
