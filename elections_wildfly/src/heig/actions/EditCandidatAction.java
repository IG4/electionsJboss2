package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Candidat;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.edit.candidats", type = "tiles")
public class EditCandidatAction extends ActionSupport implements ServletRequestAware {
	private Candidat candidat;
	private HttpServletRequest request;

	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
		String candidatId = request.getParameter("candidatId");
		if (candidatId == null || "".equals(candidatId) || " ".equals(candidatId)) {
			candidat = new Candidat();
		} else {
			try {
				candidat = elections.getCandidat(Integer.parseInt(candidatId));
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
