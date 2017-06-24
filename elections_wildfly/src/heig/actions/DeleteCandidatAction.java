package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.entite.Candidat;
import heig.exceptions.PersistException;
import heig.session.IElections;

@Results({ @Result(name = "success", type = "chain", location = "list-candidats"),
		@Result(name = "input", location = "page.list.candidats", type = "tiles") })
@SuppressWarnings("serial")
public class DeleteCandidatAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	
	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
		String candidatId = request.getParameter("candidatId");
		if (candidatId == null || "".equals(candidatId) || " ".equals(candidatId)) {
			addActionError("CandidatId invalide : " + candidatId);
		} else {
			try {
				elections.delete(Candidat.class, Integer.parseInt(candidatId));
			} catch (PersistException | NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
