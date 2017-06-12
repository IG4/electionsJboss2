package heig.actions;

import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Election;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.add.elections", type = "tiles")
public class AddElectionAction extends ActionSupport {

	private Election election;

	public String execute() throws NamingException {
		election = new Election();
		return SUCCESS;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}
}
