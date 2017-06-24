package heig.actions;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import heig.entite.Parti;
import heig.exceptions.PersistException;
import heig.session.IElections;

@Results({ @Result(name = "success", type = "chain", location = "list-partis"),
		@Result(name = "input", location = "page.edit.partis", type = "tiles") })
@SuppressWarnings("serial")
public class SavePartiAction extends ActionSupport {

	private Parti parti;

	public String execute() {
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
			elections.save(parti);
		} catch (PersistException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
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
	public void validate() {
		if ((parti.getNom() == null) || (parti.getNom().length() < 3)) {
			addActionError(getText("nom.tropcourt"));
		}
		if ((parti.getLocalite() == null) || (parti.getLocalite().length() < 3)){
			addActionError(getText("localite.vide"));
		}
		
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
			if (!elections.checkDate(parti.getDdf())) {
				addActionError(getText("date.formatincorrect"));
			}
		} catch (NamingException e1) {
			addActionError("Impossible de valider la date");
		}
	}

}
