package heig.actions;

import javax.servlet.ServletContext;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Electeur;
import heig.metier.exceptions.PersistException;
import heig.metier.session.Elections;

@Results({ @Result(name = "success", type = "chain", location = "list-electeurs"),
		@Result(name = "input", location = "page.edit", type = "tiles") })
@SuppressWarnings("serial")
public class SaveElecteurAction extends ActionSupport implements ServletContextAware {

	private Electeur electeur;
	private ServletContext servletContext;;

	public String execute() {
		Elections election = (Elections) servletContext.getAttribute("elections");
		try {
			System.out.println(electeur);
			election.saveElecteur(electeur);
		} catch (PersistException e) {
			e.printStackTrace();
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
	public void validate() {
		if ((electeur.getPrenom() == null) || (electeur.getPrenom().length() < 3)) {
			addActionError(getText("prenom.tropcourt"));
		}
		if ((electeur.getNom() == null) || (electeur.getNom().length() < 3)) {
			addActionError(getText("nom.tropcourt"));
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
