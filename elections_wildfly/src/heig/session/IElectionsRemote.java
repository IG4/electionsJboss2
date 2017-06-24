package heig.session;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import heig.entite.IPersistable;
import heig.exceptions.PersistException;

@Remote
public interface IElectionsRemote {

	IPersistable getPersistable(String queryName, Map<String, Object> params) throws PersistException;
	
	<T> List<T> getPersitableList(String queryName) throws PersistException;
}
