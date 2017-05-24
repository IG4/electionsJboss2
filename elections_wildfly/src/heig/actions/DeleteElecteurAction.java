package heig.actions;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Electeur;
import heig.metier.exceptions.PersistException;
import heig.metier.session.Elections;

@Results({ @Result(name = "success", type = "chain", location = "list-electeurs"),
		@Result(name = "input", location = "page.edit", type = "tiles") })
@SuppressWarnings("serial")
public class DeleteElecteurAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;

	@Inject
	private Elections elections;
	
	public String execute() {
		String electeurId = request.getParameter("electeurId");
		if (electeurId == null || "".equals(electeurId) || " ".equals(electeurId)) {
			addActionError("ElecteurId invalide : " + electeurId);
		} else {
			try {
				Electeur toDelete = new Electeur();
				toDelete.setId(Integer.parseInt(electeurId));
				elections.deleteElecteur(toDelete);
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
