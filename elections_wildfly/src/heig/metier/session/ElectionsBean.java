package heig.metier.session;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import heig.metier.entite.Candidat;
import heig.metier.entite.Electeur;
import heig.metier.entite.Election;
import heig.metier.entite.IPersistable;
import heig.metier.entite.Parti;
import heig.metier.exceptions.PersistException;

@Stateless
public class ElectionsBean implements IElections {

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
	 * @throws PersistException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void delete(Class clazz, Integer id) throws PersistException {
		try {
			if (id != null) {
				
				em.remove(em.find(clazz, id));
			}
			else {
				throw new PersistException(String.valueOf(id) + " invalide : null");
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
	
	@PostConstruct
	public void init() {
		/**
		 * Ppoulate db with some ppl
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			saveOrUpdate(new Electeur(null, "Doe", "John", new Date(sdf.parse("12/05/1982").getTime()), "Lausanne"));
			saveOrUpdate(new Electeur(null, "Doe", "Jane", new Date(sdf.parse("14/07/1984").getTime()), "Lausanne"));
			saveOrUpdate(new Electeur(null, "Hendrix", "Jimmy", new Date(sdf.parse("07/11/1944").getTime()), "Montreux"));
			saveOrUpdate(new Electeur(null, "Mercury", "Freddy", new Date(sdf.parse("07/11/1944").getTime()), "Montreux"));
			saveOrUpdate(new Electeur(null, "Marley", "Bob", new Date(sdf.parse("18/04/1947").getTime()), "Zürich"));
			saveOrUpdate(new Electeur(null, "Goldmann", "Jean-Jacques", new Date(sdf.parse("09/12/1953").getTime()), "Genève"));
			
			saveOrUpdate(new Candidat(null, "Morand", "Toto", new Date(sdf.parse("23/09/1957").getTime()), "Lausanne", "Parti de rien"));
			saveOrUpdate(new Candidat(null, "Brélaz", "Daniel", new Date(sdf.parse("14/02/1962").getTime()), "Lausanne", "Les Verts"));
			saveOrUpdate(new Candidat(null, "Germond", "Florence", new Date(sdf.parse("14/02/1962").getTime()), "Lausanne", "Les Verts"));
		} catch (PersistException | ParseException e) {
			e.printStackTrace();
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
	public List<Election> getElections() throws PersistException {
		try {
			return em.createQuery("Select e FROM Election e").getResultList();
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("getElections()", e);
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
	public Election getElection(Integer id) throws PersistException {
		return (Election) getPersistable(Election.class, id);
	}
	
	@Override
	public Parti getParti(Integer id) throws PersistException {
		return (Parti) getPersistable(Parti.class, id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteCandidat(Class clazz, Integer id) throws PersistException {
		delete(clazz, id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteElecteur(Class clazz, Integer id) throws PersistException {
		delete(clazz, id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void deleteElection(Class clazz, Integer id) throws PersistException {
		delete(clazz, id);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void deleteParti(Class clazz, Integer id) throws PersistException {
		delete(clazz, id);
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
	public void saveElection(Election toSave) throws PersistException {
		saveOrUpdate(toSave);
	}
	
	@Override
	public void saveParti(Parti toSave) throws PersistException {
		saveOrUpdate(toSave);
	}

}
