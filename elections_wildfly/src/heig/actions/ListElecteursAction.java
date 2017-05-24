package heig.actions;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Electeur;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;


@SuppressWarnings("serial")
@Result(name = "success", location = "page.list", type = "tiles")
public class ListElecteursAction extends ActionSupport {

	private List<Electeur> electeurs;

	public List<Electeur> getElecteurs() {
		return electeurs;
	}

	public void setElecteurs(List<Electeur> electeurs) {
		this.electeurs = electeurs;
	}

	public String execute() {
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
			electeurs = elections.getElecteurs();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (PersistException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


}