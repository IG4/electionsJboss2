package heig.actions;

import java.util.ArrayList;
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

	private List<Electeur> electeurs;
	
	private List<Candidat> candidats;

	private HttpServletRequest request;

	private String managePeople(boolean add, boolean elector, boolean all, String peopleId, String electionId, IElections elections)
			throws NumberFormatException, PersistException {
		Election election = elections.getElection(Integer.parseInt(electionId));
		if (elector) {
			if(!all){
				Electeur electeur = elections.getElecteur(Integer.parseInt(peopleId));
				if (add) {
					election.getElecteurs().add(electeur);
				}
				else {
					election.getElecteurs().remove(electeur);
				}			
			}else{
				election.getElecteurs().clear();
				election.getElecteurs().addAll(elections.getElecteurs());
			}
			
		}
		else {
			Candidat candidat = elections.getCandidat(Integer.parseInt(peopleId));
			if (add) {
				election.getCandidats().add(candidat);
			}
			else {
				election.getCandidats().remove(candidat);
			}
		}
		elections.save(election);
		return electionId;
	}
	
	private List<Electeur> filterElecteurs(List<Electeur> base, Election election) {
		if (election == null || (election.getElecteurs() == null || election.getElecteurs().isEmpty())) {
			return base;
		}
		List<Electeur> result = new ArrayList<Electeur>();
		if (base == null || base.isEmpty()) {
			return result;
		}
		for (Electeur el : base) {
			if (!election.getElecteurs().contains(el)) {
				result.add(el);
			}
		}
		return result;
	}

	private List<Candidat> filterCandidats(List<Candidat> base, Election election) {
		if (election == null || (election.getCandidats() == null || election.getCandidats().isEmpty())) {
			return base;
		}
		List<Candidat> result = new ArrayList<Candidat>();
		if (base == null || base.isEmpty()) {
			return result;
		}
		for (Candidat ct : base) {
			if (!election.getCandidats().contains(ct)) {
				result.add(ct);
			}
		}
		return result;
	}

	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
		String electionId = request.getParameter("electionId");
		try {
			String addElecteurId = request.getParameter("addElecteurId");
			String removeElecteurId = request.getParameter("removeElecteurId");
			String addCandidatId = request.getParameter("addCandidatId");
			String removeCandidatId = request.getParameter("removeCandidatId");
			String addAllCandidats = request.getParameter("addAllCandidats");
			String addAllElecteurs = request.getParameter("addAllElecteurs");
			// persist electors/candidates changes if any
			if (addElecteurId != null && !addElecteurId.isEmpty()) {
				electionId = managePeople(true, true, false, addElecteurId, request.getParameter("addElecteurElectionId"), elections);
			} else if (removeElecteurId != null && !removeElecteurId.isEmpty()) {
				electionId = managePeople(false, true, false, removeElecteurId, request.getParameter("removeElecteurElectionId"), elections);
			} else if (addCandidatId != null && !addCandidatId.isEmpty()) {
				electionId = managePeople(true, false, false, addCandidatId, request.getParameter("addCandidatElectionId"), elections);
			} else if (removeCandidatId != null && !removeCandidatId.isEmpty()) {
				electionId = managePeople(false, false, false, removeCandidatId, request.getParameter("removeCandidatElectionId"), elections);
			} 
			else if (addAllElecteurs != null){
				electionId = managePeople(true, true, true, "", addAllElecteurs, elections);
			}
			// load election and changes in electors/candidates if any
			if (electionId != null && !electionId.isEmpty()) {
				election = elections.getElection(Integer.parseInt(electionId));
			}
			// unaffected electors filtering
			electeurs = filterElecteurs(elections.getElecteurs(), election);
			// unaffected candidates filtering
			candidats = filterCandidats(elections.getCandidats(), election);
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

	public List<Candidat> getCandidats() {
		return candidats;
	}

	public void setCandidats(List<Candidat> candidats) {
		this.candidats = candidats;
	}

	public List<Electeur> getElecteurs() {
		return electeurs;
	}

	public void setElecteurs(List<Electeur> electeurs) {
		this.electeurs = electeurs;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}
}
