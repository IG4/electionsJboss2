package heig.actions;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Election;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;


@SuppressWarnings("serial")
@Result(name = "success", location = "page.list.elections", type = "tiles")
public class ListElectionsAction extends ActionSupport {

	private List<Election> elections;

	public List<Election> getElections() {
		return elections;
	}

	public void setElections(List<Election> elections) {
		this.elections = elections;
	}

	public String execute() {
		try {
			Context ctx = new InitialContext();
			IElections electionsBean = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
			elections = electionsBean.getElections();
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (PersistException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


}