package heig.metier.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import heig.metier.entite.Candidat;
import heig.metier.entite.Electeur;
import heig.metier.entite.IPersistable;
import heig.metier.entite.Parti;
import heig.metier.exceptions.PersistException;

@Stateless
public class Elections implements IElections {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Loads from database given {@code clazz} instance with matching {@code id}.
	 * 
	 * @param clazz The instance class to load
	 * @param id The id to look for
	 * @return The matching instance
	 * @throws PersistException if nothing found
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private IPersistable getPersistable(Class clazz, Integer id) throws PersistException {
		try {
			IPersistable result = (IPersistable) em.find(clazz, id);
			if (result == null) {
				throw new PersistException(String.format(clazz.getName() + " with id = %s not found", id));
			}
			return result;
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("getPersistable()", e);
		}
	}
	/**
	 * Deletes the given {@code persistable} from database.
	 * 
	 * @param persistable
	 * @throws PersistException
	 */
	private void delete(IPersistable persistable) throws PersistException {
		try {
			if (persistable != null) {
				em.remove(persistable);
			}
			else {
				throw new PersistException(persistable + " invalide : null");
			}
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("delete()", e);
		}
	}
	/**
	 * Insert ({@code id==null}) or update ({@code id!=null}) the given 
	 * {@code persistable} in database.
	 * 
	 * @param persistable
	 * @throws PersistException
	 */
	private void saveOrUpdate(IPersistable persistable) throws PersistException {
		try {
			if (persistable != null) {
				if (persistable.getId() != null) {
					em.merge(persistable);
				}
				else {
					em.persist(persistable);
				}
			}
			else {
				throw new PersistException(persistable + " invalide : null");
			}
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("saveOrUpdate()", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Candidat> getCandidats() throws PersistException {
		try {
			return em.createQuery("Select c FROM Candidat c").getResultList();
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("getCandidats()", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Electeur> getElecteurs() throws PersistException {
		try {
			return em.createQuery("Select e FROM Electeur e").getResultList();
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("getElecteurs()", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Parti> getPartis() throws PersistException {
		try {
			return em.createQuery("Select p FROM Parti p").getResultList();
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("getPartis()", e);
		}
	}

	@Override
	public Candidat getCandidat(Integer id) throws PersistException {
		return (Candidat) getPersistable(Candidat.class, id);
	}

	@Override
	public Electeur getElecteur(Integer id) throws PersistException {
		return (Electeur) getPersistable(Electeur.class, id);
	}

	@Override
	public Parti getParti(Integer id) throws PersistException {
		return (Parti) getPersistable(Parti.class, id);
	}

	@Override
	public void deleteCandidat(Candidat toDelete) throws PersistException {
		delete(toDelete);
	}

	@Override
	public void deleteElecteur(Electeur toDelete) throws PersistException {
		delete(toDelete);
	}

	@Override
	public void deleteParti(Parti toDelete) throws PersistException {
		delete(toDelete);
	}

	@Override
	public void saveCandidat(Candidat toSave) throws PersistException {
		saveOrUpdate(toSave);
	}

	@Override
	public void saveElecteur(Electeur toSave) throws PersistException {
		saveOrUpdate(toSave);
	}

	@Override
	public void saveParti(Parti toSave) throws PersistException {
		saveOrUpdate(toSave);
	}
}
