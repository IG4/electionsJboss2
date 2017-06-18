package heig.metier.session;

import java.util.List;

import javax.ejb.Remote;

import heig.metier.entite.Election;
import heig.metier.exceptions.PersistException;

@Remote
public interface IElectionsRemote {

	List<Election> getElections() throws PersistException;
}
