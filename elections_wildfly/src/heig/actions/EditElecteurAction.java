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

import heig.metier.entite.Electeur;
import heig.metier.entite.NamedQueriesConstants;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.edit.electeurs", type = "tiles")
public class EditElecteurAction extends ActionSupport implements ServletRequestAware {
	private Electeur electeur;
	private HttpServletRequest request;

	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
		String electeurId = request.getParameter("electeurId");
		if (electeurId == null || "".equals(electeurId) || " ".equals(electeurId)) {
			electeur = new Electeur();
		} else {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(NamedQueriesConstants.ELECTOR_BY_ID_QUERY_PARAM, Integer.parseInt(electeurId));
				electeur = (Electeur) elections.getPersistable(NamedQueriesConstants.ELECTOR_BY_ID_QUERY_NAME, params);
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
