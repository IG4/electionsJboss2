package heig.client;


import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import heig.client.view.ElectionsFrame;
import heig.metier.entite.Election;
import heig.metier.entite.NamedQueriesConstants;
import heig.metier.exceptions.PersistException;
import heig.metier.session.IElectionsRemote;

public class ElectionsClient {
	
	public static void main(String[] args) {
		try {
			Properties prop = new Properties();
			prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			Context context = new InitialContext(prop);
			Object obj = context.lookup("ejb:Elections/elections_wildfly/ElectionsBean!heig.metier.session.IElectionsRemote");
			IElectionsRemote elections = (IElectionsRemote) obj;
			List<Election> electionsList = elections.getPersitableList(NamedQueriesConstants.ELECTION_LIST_QUERY_NAME);
			new ElectionsFrame(electionsList);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (PersistException e) {
			e.printStackTrace();
		}
	}
}
    