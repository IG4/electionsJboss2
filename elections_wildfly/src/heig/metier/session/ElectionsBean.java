package heig.metier.session;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import heig.metier.entite.Candidat;
import heig.metier.entite.Election;
import heig.metier.entite.IPersistable;
import heig.metier.entite.Parti;
import heig.metier.exceptions.PersistException;

@Stateless
public class ElectionsBean implements IElections, IElectionsRemote {

	@PersistenceContext
	private EntityManager em;

	private void loadLazyElements(IPersistable p) {
		if (p instanceof Candidat) {
			if (((Candidat) p).getParti() != null) {
				((Candidat) p).getParti().getNom();
			}
		}
		else if (p instanceof Election) {
			((Election) p).getCandidats().size();
			((Election) p).getElecteurs().size();
			((Election) p).getVotes().size();
		}
		else if (p instanceof Parti) {
			((Parti) p).getCandidats().size();
		}
	}
	
	@Override
	public Boolean checkDate(Date toCheck) {
		if (toCheck == null) {
			return Boolean.FALSE;
		}
		Boolean result = Boolean.TRUE;
		// Verify Date object is correct
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		try {
			cal.setTime(toCheck);
		    cal.getTime();
		}
		catch (Exception e) {
		  result = Boolean.FALSE;
		}
		if (result) {
			try { // check mysql date limitations : https://dev.mysql.com/doc/refman/5.6/en/datetime.html
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Calendar min = Calendar.getInstance();
				min.setTime(new Date(sdf.parse("01/01/1000").getTime()));
				Calendar max = Calendar.getInstance();
				max.setTime(new Date(sdf.parse("31/12/9999").getTime()));
				if (toCheck.before(min.getTime()) || toCheck.after(max.getTime())) {
					result = Boolean.FALSE;
				}
			} catch (ParseException e) {
				result = Boolean.FALSE;
			}
		}
		return result;
	}
	
	@Override
	public IPersistable getPersistable(String queryName, Map<String, Object> params) throws PersistException {
		Query query = em.createNamedQuery(queryName);
		for (Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		IPersistable result = (IPersistable) query.getSingleResult();
		loadLazyElements(result);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getPersitableList(String queryName) throws PersistException {
		Query query = em.createNamedQuery(queryName);
		List<T> result = (List<T>)(List<?>) query.getResultList();
		for (T el : result) {
			loadLazyElements((IPersistable) el);
		}
		return result;
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@TransactionAttribute(value=TransactionAttributeType.REQUIRES_NEW)
	public void delete(Class clazz, Integer id) throws PersistException {
		try {
			if (id != null) {
				em.remove(em.find(clazz, id));
			}
			else {
				throw new PersistException(clazz.getName() + " avec id invalide : null");
			}
		}
		catch (PersistenceException e) {
			throw new PersistException("delete() : " + clazz.getName() + " avec id = " + String.valueOf(id), e);
		}
	}

	@Override
	@TransactionAttribute(value=TransactionAttributeType.REQUIRES_NEW)
	public void save(IPersistable toSave) throws PersistException {
		try {
			if (toSave == null) {
				throw new PersistException("Paramètre invalide : null");
			}
			if (toSave.getId() != null) {
				em.merge(toSave);
			}
			else {
				em.persist(toSave);
			}
		}
		catch (PersistenceException e) {
			throw new PersistException("save()", e);
		}
	}
}
