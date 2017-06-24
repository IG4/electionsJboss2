package heig.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import heig.entite.Candidat;
import heig.entite.NamedQueriesConstants;
import heig.entite.Parti;
import heig.exceptions.PersistException;
import heig.session.IElections;

@SuppressWarnings("serial")
@Result(name = "success", location = "page.edit.partis", type = "tiles")
public class EditPartiAction extends ActionSupport implements ServletRequestAware {
	private Parti parti;
	private List<Candidat> candidats;
	private HttpServletRequest request;

	private String manageCandidats(boolean add, String candidatId, String partiId, IElections elections)
			throws NumberFormatException, PersistException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(NamedQueriesConstants.PARTI_BY_ID_QUERY_PARAM, Integer.parseInt(partiId));
		Parti parti = (Parti) elections.getPersistable(NamedQueriesConstants.PARTI_BY_ID_QUERY_NAME, params);
		
		params = new HashMap<String, Object>();
		params.put(NamedQueriesConstants.CANDIDATE_BY_ID_QUERY_PARAM, Integer.parseInt(candidatId));
		Candidat candidat = (Candidat) elections.getPersistable(NamedQueriesConstants.CANDIDATE_BY_ID_QUERY_NAME, params);
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
		IElections elections = (IElections) ctx.lookup(EJBNamingConstants.EJB_ELECTIONS);
		String partiId = request.getParameter("partiId");
		try {
			String addCandidatId = request.getParameter("addCandidatId");
			String removeCandidatId = request.getParameter("removeCandidatId");
			if (addCandidatId != null && !addCandidatId.isEmpty()) {
				partiId = manageCandidats(true, addCandidatId, request.getParameter("addCandidatPartiId"), elections);
			} else if (removeCandidatId != null && !removeCandidatId.isEmpty()) {
				partiId = manageCandidats(false, removeCandidatId, request.getParameter("removeCandidatPartiId"), elections);
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(NamedQueriesConstants.PARTI_BY_ID_QUERY_PARAM, Integer.parseInt(partiId));
			parti = (Parti) elections.getPersistable(NamedQueriesConstants.PARTI_BY_ID_QUERY_NAME, params);
			// unaffected candidates filtering
			candidats = filterCandidats(elections.getPersitableList(NamedQueriesConstants.CANDIDATE_LIST_QUERY_NAME), parti);
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
