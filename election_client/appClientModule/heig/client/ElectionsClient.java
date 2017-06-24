package heig.client;


import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import heig.actions.EJBNamingConstants;
import heig.session.IElectionsRemote;
import heig.view.ElectionsFrame;

public class ElectionsClient {
	
	public static void main(String[] args) {
		try {
			Properties prop = new Properties();
			prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			Context context = new InitialContext(prop);
			IElectionsRemote elections = (IElectionsRemote) context.lookup(EJBNamingConstants.EJB_REMOTE_ELECTIONS);
			new ElectionsFrame(elections);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
    