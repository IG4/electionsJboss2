package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Candidat;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@Results({ @Result(name = "success", type = "chain", location = "list-candidats"),
		@Result(name = "input", location = "page.edit.candidats", type = "tiles") })
@SuppressWarnings("serial")
public class SaveCandidatAction extends ActionSupport {

	private Candidat candidat;

	public String execute() {
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
			elections.saveCandidat(candidat);
		} catch (PersistException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	@Override
	public void validate() {
		if ((candidat.getPrenom() == null) || (candidat.getPrenom().length() < 3)) {
			addActionError(getText("prenom.tropcourt"));
		}
		if ((candidat.getNom() == null) || (candidat.getNom().length() < 3)) {
			addActionError(getText("nom.tropcourt"));
		}
	}

}
