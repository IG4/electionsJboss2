package heig.actions;

import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Election;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@Results({ @Result(name = "success", type = "chain", location = "list-elections"),
		@Result(name = "input", location = "page.edit.elections", type = "tiles") })
@SuppressWarnings("serial")
public class SaveElectionAction extends ActionSupport {

	private Election election;

	public String execute() {
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
			elections.saveElection(election);
		} catch (PersistException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	@Override
	public void validate() {
		if ((election.getCode() == null) || (election.getCode().length() < 3)) {
			addActionError(getText("prenom.tropcourt"));
		}
		if ((election.getNom() == null) || (election.getNom().length() < 3)) {
			addActionError(getText("nom.tropcourt"));
		}
		if ((election.getDebut() == null) || (election.getDebut().before(new Date()))) {
			addActionError(getText("debut.invalide"));
		}

		if ((election.getFin() == null) || (election.getFin().before(new Date()))) {
			addActionError(getText("fin.invalide"));
		}
	}

}
