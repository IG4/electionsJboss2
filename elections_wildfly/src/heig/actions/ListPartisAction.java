package heig.actions;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import heig.entite.NamedQueriesConstants;
import heig.entite.Parti;
import heig.exceptions.PersistException;
import heig.session.IElections;


@SuppressWarnings("serial")
@Result(name = "success", location = "page.list.partis", type = "tiles")
public class ListPartisAction extends ActionSupport {

	private List<Parti> partis;

	public List<Parti> getPartis() {
		return partis;
	}

	public void setPartis(List<Parti> partis) {
		this.partis = partis;
	}

	public String execute() {
		try {
			Context ctx = new InitialContext();
			IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
			partis = elections.getPersitableList(NamedQueriesConstants.PARTI_LIST_QUERY_NAME);
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		catch (PersistException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


}