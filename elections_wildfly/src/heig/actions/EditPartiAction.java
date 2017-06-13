package heig.actions;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.metier.entite.Candidat;
import heig.metier.entite.Parti;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElections;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.edit.partis", type = "tiles")
public class EditPartiAction extends ActionSupport implements ServletRequestAware {
	private Parti parti;
	private List<Candidat> candidats;
	private HttpServletRequest request;

	private String manageCandidats(boolean add, String candidatId, String partiId, IElections elections)
			throws NumberFormatException, PersistException {
		Parti parti = elections.getParti(Integer.parseInt(partiId));
		Candidat candidat = elections.getCandidat(Integer.parseInt(candidatId));
		if (add) {
			parti.addCandidat(candidat);
		}
		else {
			parti.removeCandidat(candidat);
			elections.save(candidat);			
		}			
		elections.save(parti);
		return partiId;
	}
	
	private List<Candidat> filterCandidats(List<Candidat> base, Parti parti) {
		List<Candidat> result = new ArrayList<Candidat>();
		for (Candidat ct : base) {
			if (ct.getParti() == null && !parti.getCandidats().contains(ct)) {
				result.add(ct);
			}
		}
		return result;
	}
	
	public String execute() throws NamingException {
		Context ctx = new InitialContext();
		IElections elections = (IElections) ctx.lookup("java:global/elections_wildfly/ElectionsBean!heig.metier.session.IElections");
		String partiId = request.getParameter("partiId");
		try {
			String addCandidatId = request.getParameter("addCandidatId");
			String removeCandidatId = request.getParameter("removeCandidatId");
			if (addCandidatId != null && !addCandidatId.isEmpty()) {
				partiId = manageCandidats(true, addCandidatId, request.getParameter("addCandidatPartiId"), elections);
			} else if (removeCandidatId != null && !removeCandidatId.isEmpty()) {
				partiId = manageCandidats(false, removeCandidatId, request.getParameter("removeCandidatPartiId"), elections);
			}
			parti = elections.getParti(Integer.parseInt(partiId));
			// unaffected candidates filtering
			candidats = filterCandidats(elections.getCandidats(), parti);
		} catch (NumberFormatException | PersistException e) {
			addActionError("Une erreur s'est produite pendant le chargement du parti avec id = " + partiId);
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

	public List<Candidat> getCandidats() {
		return candidats;
	}

	public void setCandidats(List<Candidat> candidats) {
		this.candidats = candidats;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
