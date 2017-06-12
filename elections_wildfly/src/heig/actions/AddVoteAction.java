package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Election;
import heig.metier.entite.Vote;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.add.vote", type = "tiles")
public class AddVoteAction extends ActionSupport implements ServletRequestAware {
	private Election election;
	
	private Vote vote;
	
	private HttpServletRequest request;

	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		try {
			vote = (Vote) ctx.lookup("Vote");
		}
		catch (NameNotFoundException e) {
			vote = new Vote();
			ctx.bind("Vote", vote);
		}
		
		IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
		String electionId = request.getParameter("electionId");
		try {
			String addElecteurId = request.getParameter("addElecteurId");
			String addCandidatId = request.getParameter("addCandidatId");
			String removeVoteId = request.getParameter("removeVoteId");
			if (addElecteurId != null && !addElecteurId.isEmpty()) {
				vote.setElecteur(elections.getElecteur(Integer.parseInt(addElecteurId)));
				electionId = request.getParameter("addElecteurElectionId");
			} else if (addCandidatId != null && !addCandidatId.isEmpty()) {
				vote.setCandidat(elections.getCandidat(Integer.parseInt(addCandidatId)));
				electionId = request.getParameter("addCandidatElectionId");
			} else if (removeVoteId != null && !removeVoteId.isEmpty()) {
				elections.delete(Vote.class, Integer.parseInt(removeVoteId));
				electionId = request.getParameter("removeVoteElectionId");
			}
			
			if (electionId == null || "".equals(electionId) || " ".equals(electionId)) {
				addActionError("Election non trouvée");
			} else {
				election = elections.getElection(Integer.parseInt(electionId));
				if (vote.getElecteur() != null) {
					election.getElecteurs().remove(vote.getElecteur());
				}
				for (Vote v : election.getVotes()) {
					election.getElecteurs().remove(v.getElecteur());
				}
				vote.setElection(election);
			}
		} catch (NumberFormatException | PersistException e) {
			addActionError("Un problème est survenu : " + e.getMessage());
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

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
