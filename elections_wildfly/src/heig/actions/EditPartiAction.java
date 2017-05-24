package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Parti;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.edit.partis", type = "tiles")
public class EditPartiAction extends ActionSupport implements ServletRequestAware {
	private Parti parti;
	private HttpServletRequest request;

	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
		String partiId = request.getParameter("partiId");
		if (partiId == null || "".equals(partiId) || " ".equals(partiId)) {
			parti = new Parti();
		} else {
			try {
				parti = elections.getParti(Integer.parseInt(partiId));
			} catch (NumberFormatException | PersistException e) {
				addActionError("Une erreur s'est produite pendant le chargement du parti avec id = " + partiId);
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public Parti getParti() {
		return parti;
	}

	public void setParti(Parti parti) {
		this.parti = parti;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
