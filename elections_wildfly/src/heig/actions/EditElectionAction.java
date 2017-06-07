package heig.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Candidat;
import heig.metier.entite.Electeur;
import heig.metier.entite.Election;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.edit.elections", type = "tiles")
public class EditElectionAction extends ActionSupport implements ServletRequestAware {
	private Election election;
	private List<Electeur> electeursDispo;
	private List<Candidat> candidatsDispo;
	
	private HttpServletRequest request;

	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		IElections elections = (IElections) ctx
				.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
		String electionId = request.getParameter("electionId");
		try {
			if (electionId == null || "".equals(electionId) || " ".equals(electionId)) {
				election = new Election();
			} else {
				election = elections.getElection(Integer.parseInt(electionId));
			}
			
			electeursDispo = elections.getElecteurs();
			if (!electeursDispo.isEmpty()) {
				for (Electeur el : election.getElecteurs()) {
					electeursDispo.remove(el);
				}
			}
			candidatsDispo = elections.getCandidats();
			if (!candidatsDispo.isEmpty()) {
				for (Candidat cd : election.getCandidats()) {
					candidatsDispo.remove(cd);
				}
			}
		} catch (NumberFormatException | PersistException e) {
			addActionError("Une erreur s'est produite pendant le chargement de l'�lection avec id = " + electionId);
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public List<Electeur> getElecteursDispo() {
		return electeursDispo;
	}

	public void setElecteursDispo(List<Electeur> electeursDispo) {
		this.electeursDispo = electeursDispo;
	}

	public List<Candidat> getCandidatsDispo() {
		return candidatsDispo;
	}

	public void setCandidatsDispo(List<Candidat> candidatsDispo) {
		this.candidatsDispo = candidatsDispo;
	}
	


}
