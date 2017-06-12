package heig.metier.session;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
	
	@PostConstruct
	public void init() {
		/**
		 * Ppoulate db with some ppl
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			save(new Electeur(null, "Doe", "John", new Date(sdf.parse("12/05/1982").getTime()), "Lausanne"));
			save(new Electeur(null, "Doe", "Jane", new Date(sdf.parse("14/07/1984").getTime()), "Lausanne"));
			save(new Electeur(null, "Hendrix", "Jimmy", new Date(sdf.parse("07/11/1944").getTime()), "Montreux"));
			save(new Electeur(null, "Mercury", "Freddy", new Date(sdf.parse("07/11/1944").getTime()), "Montreux"));
			save(new Electeur(null, "Marley", "Bob", new Date(sdf.parse("18/04/1947").getTime()), "Zürich"));
			save(new Electeur(null, "Goldmann", "Jean-Jacques", new Date(sdf.parse("09/12/1953").getTime()), "Genève"));
			
			save(new Candidat(null, "Leuthard", "Doris", new Date(sdf.parse("21/04/1969").getTime()), "Araau"));
			save(new Candidat(null, "Broulis", "Pascal", new Date(sdf.parse("03/10/1967").getTime()), "Lausanne"));
			save(new Candidat(null, "Morand", "Toto", new Date(sdf.parse("23/09/1957").getTime()), "Lausanne"));
			save(new Candidat(null, "Brélaz", "Daniel", new Date(sdf.parse("14/02/1962").getTime()), "Lausanne"));
			save(new Candidat(null, "Germond", "Florence", new Date(sdf.parse("14/02/1962").getTime()), "Lausanne"));
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, 10);
			
			Election election = new Election(null, "Test", "MD45", new Date(new java.util.Date().getTime()), new Date(cal.getTimeInMillis()));
			List<Candidat> candidatsDispo = getCandidats();
			election.getCandidats().add(candidatsDispo.get(0));
			election.getCandidats().add(candidatsDispo.get(1));
			List<Electeur> electeursDispo = getElecteurs();
			election.getElecteurs().add(electeursDispo.get(0));
			election.getElecteurs().add(electeursDispo.get(1));
			save(election);
			
			candidatsDispo = getCandidats();
			Parti parti = new Parti(null, "PDC", new Date(sdf.parse("12/09/1902").getTime()), "Bern");
			parti.addCandidat(candidatsDispo.get(0));
			save(parti);
			
			parti = new Parti(null, "PLR", new Date(sdf.parse("07/02/1904").getTime()), "Basel");
			parti.addCandidat(candidatsDispo.get(1));
			save(parti);
			
			parti = new Parti(null, "Parti de rien", new Date(sdf.parse("05/03/2012").getTime()), "Lausanne");
			parti.addCandidat(candidatsDispo.get(2));
			save(parti);
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
		Election result = (Election) getPersistable(Election.class, id);
		System.out.println("electeurs : " + result.getElecteurs().size());
		System.out.println("candidats : " + result.getCandidats().size());
		System.out.println("votes : " + result.getVotes().size());
		return result;
	}
	
	@Override
	public Parti getParti(Integer id) throws PersistException {
		Parti result = (Parti) getPersistable(Parti.class, id);
		System.out.println("candidats : " + result.getCandidats().size());
		return result;
	}

	@TransactionAttribute(value=TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
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
			e.printStackTrace();
			throw new PersistenceException("delete() : " + clazz.getName() + " avec id = " + String.valueOf(id), e);
		}
	}

	@TransactionAttribute(value=TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void save(IPersistable toSave) throws PersistException {
		try {
			if (toSave == null) {
				throw new PersistException(toSave + " invalide : null");
			}

			if (toSave.getId() != null) {
				em.merge(toSave);
			}
			else {
				em.persist(toSave);
			}
		}
		catch (PersistenceException e) {
			e.printStackTrace();
			throw new PersistenceException("save()", e);
		}
	}
}
