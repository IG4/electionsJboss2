package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Electeur;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.edit", type = "tiles")
public class EditElecteurAction extends ActionSupport implements ServletRequestAware {
	private Electeur electeur;
	private HttpServletRequest request;

	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
		String electeurId = request.getParameter("electeurId");
		if (electeurId == null || "".equals(electeurId) || " ".equals(electeurId)) {
			electeur = new Electeur();
		} else {
			try {
				electeur = elections.getElecteur(Integer.parseInt(electeurId));
			} catch (NumberFormatException | PersistException e) {
				addActionError("Une erreur s'est produite pendant le chargement de l'électeur avec id = " + electeurId);
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public Electeur getElecteur() {
		return electeur;
	}

	public void setElecteur(Electeur electeur) {
		this.electeur = electeur;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
