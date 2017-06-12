package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Vote;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@Results({ @Result(name = "success", type = "chain", location = "list-elections"),
		@Result(name = "input", location = "page.list.elections", type = "tiles") })
@SuppressWarnings("serial")
public class SaveVoteAction extends ActionSupport {

	private Vote vote;

	public String execute() {
		try {
			Context ctx = new InitialContext();
			vote = (Vote) ctx.lookup("Vote");
			IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
			elections.save(vote);
			ctx.unbind("Vote");
		} catch (PersistException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	@Override
	public void validate() {
		try {
			Context ctx = new InitialContext();
			vote = (Vote) ctx.lookup("Vote");
			if (vote.getCandidat() == null) {
				addActionError("candidat.invalide");
			}
			if (vote.getElecteur() == null) {
				addActionError("electeur.invalide");
			}
			if (vote.getElection() == null) {
				addActionError("election.invalide");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
