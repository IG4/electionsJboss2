package heig.actions;

import java.sql.Date;
import java.util.Calendar;

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

	private Boolean isBeforeNow(Date toCheck, Date toRefer) {
		Boolean result = Boolean.FALSE;
		Calendar reference = Calendar.getInstance();
		reference.setTimeInMillis(toRefer.getTime());
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(toCheck.getTime());
		if (cal.get(Calendar.YEAR) < reference.get(Calendar.YEAR)) {
			result = Boolean.TRUE;
		}
		else if (cal.get(Calendar.YEAR) == reference.get(Calendar.YEAR)) {
			if (cal.get(Calendar.DAY_OF_YEAR) < reference.get(Calendar.DAY_OF_YEAR)) {
				result = Boolean.TRUE;
			}
		}
		return result;
	}
	
	public String execute() {
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
			Election toSave = election;
			if (election.getId() != null) {// Problem to synchronize collections with the view => reload election before update or loose collections
				toSave = elections.getElection(election.getId());
				toSave.setCode(election.getCode());
				toSave.setDebut(election.getDebut());
				toSave.setFin(election.getFin());
				toSave.setNom(election.getNom());
			}
			elections.save(toSave);
		} catch (PersistException e) {
			addActionError("Une erreur s'est produite lors de la persistance : " + e.getMessage());
			e.printStackTrace();
		} catch (NamingException e) {
			addActionError("Une erreur s'est produite lors de la récupération de l'EJB : " + e.getMessage());
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
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
			if ((election.getDebut() == null) || 
					isBeforeNow(election.getDebut(), new Date(Calendar.getInstance().getTimeInMillis())) || 
					!elections.checkDate(election.getDebut())) {
				addActionError(getText("debut.invalide"));
			}
			if ((election.getFin() == null) || 
					isBeforeNow(election.getFin(), new Date(Calendar.getInstance().getTimeInMillis())) || 
					!elections.checkDate(election.getFin())) {
				addActionError(getText("fin.invalide"));
			}
		} catch (NamingException e1) {
			addActionError("Impossible de valider la date");
		}
	}

}
