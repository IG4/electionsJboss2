package heig.actions;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Candidat;
import heig.metier.entite.Electeur;
import heig.metier.entite.Election;
import heig.metier.entite.NamedQueriesConstants;
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
		IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
		String electionId = request.getParameter("electionId");
		try {
			String addElecteurId = request.getParameter("addElecteurId");
			String addCandidatId = request.getParameter("addCandidatId");
			String removeVoteId = request.getParameter("removeVoteId");
			Map<String, Object> params = new HashMap<String, Object>();
			if (addElecteurId != null && !addElecteurId.isEmpty()) {
				params.put(NamedQueriesConstants.ELECTOR_BY_ID_QUERY_PARAM, Integer.parseInt(addElecteurId));
				vote.setElecteur((Electeur) elections.getPersistable(NamedQueriesConstants.ELECTOR_BY_ID_QUERY_NAME, params));
				electionId = request.getParameter("addElecteurElectionId");
			} else if (addCandidatId != null && !addCandidatId.isEmpty()) {
				params.put(NamedQueriesConstants.CANDIDATE_BY_ID_QUERY_PARAM, Integer.parseInt(addCandidatId));
				vote.setCandidat((Candidat) elections.getPersistable(NamedQueriesConstants.CANDIDATE_BY_ID_QUERY_NAME, params));
				electionId = request.getParameter("addCandidatElectionId");
			} else if (removeVoteId != null && !removeVoteId.isEmpty()) {
				elections.delete(Vote.class, Integer.parseInt(removeVoteId));
				electionId = request.getParameter("removeVoteElectionId");
			}
			
			if (electionId == null || "".equals(electionId) || " ".equals(electionId)) {
				addActionError("Election non trouvée");
			} else {
				params = new HashMap<String, Object>();
				params.put(NamedQueriesConstants.ELECTION_BY_ID_QUERY_PARAM, Integer.parseInt(electionId));
				election = (Election) elections.getPersistable(NamedQueriesConstants.ELECTION_BY_ID_QUERY_NAME, params);
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
