package heig.actions;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import heig.entite.Candidat;
import heig.entite.NamedQueriesConstants;
import heig.exceptions.PersistException;
import heig.session.IElections;


@SuppressWarnings("serial")
@Result(name = "success", location = "page.list.candidats", type = "tiles")
public class ListCandidatsAction extends ActionSupport {

	private List<Candidat> candidats;

	public List<Candidat> getCandidats() {
		return candidats;
	}

	public void setCandidats(List<Candidat> candidats) {
		this.candidats = candidats;
	}

	public String execute() {
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
			candidats = elections.getPersitableList(NamedQueriesConstants.CANDIDATE_LIST_QUERY_NAME);
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (PersistException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


}