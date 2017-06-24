package heig.actions;

import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.entite.Candidat;
import heig.entite.NamedQueriesConstants;
import heig.exceptions.PersistException;
import heig.session.IElections;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.edit.candidats", type = "tiles")
public class EditCandidatAction extends ActionSupport implements ServletRequestAware {
	private Candidat candidat;
	private HttpServletRequest request;

	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
		String candidatId = request.getParameter("candidatId");
		if (candidatId == null || "".equals(candidatId) || " ".equals(candidatId)) {
			candidat = new Candidat();
		} else {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(NamedQueriesConstants.CANDIDATE_BY_ID_QUERY_PARAM, Integer.parseInt(candidatId));
				candidat = (Candidat) elections.getPersistable(NamedQueriesConstants.CANDIDATE_BY_ID_QUERY_NAME, params);
			} catch (NumberFormatException | PersistException e) {
				addActionError("Une erreur s'est produite pendant le chargement du candidat avec id = " + candidatId);
				e.printStackTrace();
			}
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
