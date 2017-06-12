package heig.metier.session;

import java.util.List;

import javax.ejb.Local;

import heig.metier.entite.Candidat;
import heig.metier.entite.Electeur;
import heig.metier.entite.Election;
import heig.metier.entite.IPersistable;
import heig.metier.entite.Parti;
import heig.metier.exceptions.PersistException;

@Local
public interface IElections {

	List<Candidat> getCandidats() throws PersistException;
	
	List<Electeur> getElecteurs() throws PersistException;
	
	List<Election> getElections() throws PersistException;
	
	List<Parti> getPartis() throws PersistException;
	
	Candidat getCandidat(Integer id) throws PersistException;
	
	Electeur getElecteur(Integer id) throws PersistException;
	
	Election getElection(Integer id) throws PersistException;
	
	Parti getParti(Integer id) throws PersistException;

	@SuppressWarnings("rawtypes")
	void delete(Class clazz, Integer id) throws PersistException;
	
	void save(IPersistable toSave) throws PersistException;
}
