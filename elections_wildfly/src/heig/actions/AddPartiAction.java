package heig.actions;

import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Parti;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.add.partis", type = "tiles")
public class AddPartiAction extends ActionSupport {

	private Parti parti;
	
	public String execute() throws NamingException {
		parti = new Parti();
		return SUCCESS;
	}

	public Parti getParti() {
		return parti;
	}

	public void setParti(Parti parti) {
		this.parti = parti;
	}
}
