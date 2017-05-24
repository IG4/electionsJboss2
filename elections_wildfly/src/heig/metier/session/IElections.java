package heig.metier.session;

import java.util.List;

import javax.ejb.Local;

import heig.metier.entite.Candidat;
import heig.metier.entite.Electeur;
import heig.metier.entite.Parti;
import heig.metier.exceptions.PersistException;

@Local
public interface IElections {

	List<Candidat> getCandidats() throws PersistException;
	
	List<Electeur> getElecteurs() throws PersistException;
	
	List<Parti> getPartis() throws PersistException;
	
	Candidat getCandidat(Integer id) throws PersistException;
	
	Electeur getElecteur(Integer id) throws PersistException;
	
	Parti getParti(Integer id) throws PersistException;
	
	void deleteCandidat(Candidat toDelete) throws PersistException;
	
	void deleteElecteur(Electeur toDelete) throws PersistException;
	
	void deleteParti(Parti toDelete) throws PersistException;
	
	void saveCandidat(Candidat toSave) throws PersistException;
	
	void saveElecteur(Electeur toSave) throws PersistException;
	
	void saveParti(Parti toSave) throws PersistException;
	
}
