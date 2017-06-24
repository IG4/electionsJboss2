package heig.metier.session;

import java.sql.Date;

import javax.ejb.Local;

import heig.metier.entite.IPersistable;
import heig.metier.exceptions.PersistException;

@Local
public interface IElections extends IElectionsRemote {

	Boolean checkDate(Date toCheck);
	
	@SuppressWarnings("rawtypes")
	void delete(Class clazz, Integer id) throws PersistException;
	
	void save(IPersistable toSave) throws PersistException;
}
