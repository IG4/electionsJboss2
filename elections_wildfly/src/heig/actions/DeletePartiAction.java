package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Parti;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@Results({ @Result(name = "success", type = "chain", location = "list-partis"),
		@Result(name = "input", location = "page.list.partis", type = "tiles") })
@SuppressWarnings("serial")
public class DeletePartiAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	
	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
		String partiId = request.getParameter("partiId");
		if (partiId == null || "".equals(partiId) || " ".equals(partiId)) {
			addActionError("partiId invalide : " + partiId);
		} else {
			try {
				elections.deleteParti(Parti.class, Integer.parseInt(partiId));
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
