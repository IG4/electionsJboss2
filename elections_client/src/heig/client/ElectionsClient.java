package heig.client;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import heig.client.view.ElectionsFrame;
import heig.metier.entite.Candidat;
import heig.metier.entite.Electeur;
import heig.metier.entite.Election;
import heig.metier.entite.Parti;
import heig.metier.entite.Vote;
import heig.metier.session.IElectionsRemote;

public class ElectionsClient {
	
	private static List<Election> getElections() {
		List<Election> elections = new ArrayList<Election>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			
			Election election = new Election(1, "Test 1", "TST1", new Date(cal.getTimeInMillis()), new Date(cal.getTimeInMillis()));
			Election election2 = new Election(2, "Test 2", "TST2", new Date(cal.getTimeInMillis()), new Date(cal.getTimeInMillis()));
			
			Electeur electeurDj = new Electeur(1, "Doe", "John", new Date(sdf.parse("12/05/1982").getTime()), "Lausanne");
			election.getElecteurs().add(electeurDj);
			election2.getElecteurs().add(electeurDj);
			
			Electeur electeurDj2 = new Electeur(2, "Doe", "Jane", new Date(sdf.parse("14/07/1984").getTime()), "Lausanne");
			election.getElecteurs().add(electeurDj2);
			election2.getElecteurs().add(electeurDj2);
			
			Electeur electeurHj = new Electeur(3, "Hendrix", "Jimmy", new Date(sdf.parse("07/11/1944").getTime()), "Montreux");
			election.getElecteurs().add(electeurHj);
			election2.getElecteurs().add(electeurHj);
			
			Electeur electeurMf = new Electeur(4, "Mercury", "Freddy", new Date(sdf.parse("07/11/1944").getTime()), "Montreux");
			election.getElecteurs().add(electeurMf);
			election2.getElecteurs().add(electeurMf);
			
			Electeur electeurMb = new Electeur(5, "Marley", "Bob", new Date(sdf.parse("18/04/1947").getTime()), "Zürich");
			election.getElecteurs().add(electeurMb);
			election2.getElecteurs().add(electeurMb);
			
			Electeur electeurGj = new Electeur(6, "Goldmann", "Jean-Jacques", new Date(sdf.parse("09/12/1953").getTime()), "Genève");
			election.getElecteurs().add(electeurGj);
			election2.getElecteurs().add(electeurGj);
			
			Parti parti = new Parti(1, "PDC", new Date(sdf.parse("12/09/1902").getTime()), "Bern");
			
			Candidat candidatLd = new Candidat(1, "Leuthard", "Doris", new Date(sdf.parse("21/04/1969").getTime()), "Araau");
			parti.addCandidat(candidatLd);
			election.getCandidats().add(candidatLd);
			election2.getCandidats().add(candidatLd);
			
			Candidat candidatBp = new Candidat(2, "Broulis", "Pascal", new Date(sdf.parse("03/10/1967").getTime()), "Lausanne");
			parti.addCandidat(candidatBp);
			election.getCandidats().add(candidatBp);
			election2.getCandidats().add(candidatBp);
			
			parti = new Parti(2, "Parti de rien", new Date(sdf.parse("05/03/2012").getTime()), "Lausanne");
			
			Candidat candidatMt = new Candidat(3, "Morand", "Toto", new Date(sdf.parse("23/09/1957").getTime()), "Lausanne");
			parti.addCandidat(candidatMt);
			election.getCandidats().add(candidatMt);
			election2.getCandidats().add(candidatMt);
			
			parti = new Parti(3, "PLR", new Date(sdf.parse("07/02/1904").getTime()), "Basel");
			
			Candidat candidatBd = new Candidat(4, "Brélaz", "Daniel", new Date(sdf.parse("14/02/1962").getTime()), "Lausanne");
			parti.addCandidat(candidatBd);
			election.getCandidats().add(candidatBd);
			election2.getCandidats().add(candidatBd);
			
			Candidat candidatGf = new Candidat(5, "Germond", "Florence", new Date(sdf.parse("14/02/1962").getTime()), "Lausanne");
			parti.addCandidat(candidatGf);
			election.getCandidats().add(candidatGf);
			election2.getCandidats().add(candidatGf);
			
			Vote vote = new Vote(1, candidatMt, electeurDj);
			election.getVotes().add(vote);
			vote = new Vote(2, candidatLd, electeurDj2);
			election.getVotes().add(vote);
			vote = new Vote(3, candidatBp, electeurHj);
			election.getVotes().add(vote);
			vote = new Vote(4, candidatBd, electeurMf);
			election.getVotes().add(vote);
			vote = new Vote(5, candidatGf, electeurMb);
			election.getVotes().add(vote);
			vote = new Vote(6, candidatLd, electeurGj);
			election.getVotes().add(vote);
			
			vote = new Vote(1, candidatLd, electeurDj);
			election2.getVotes().add(vote);
			vote = new Vote(2, candidatBp, electeurDj2);
			election2.getVotes().add(vote);
			vote = new Vote(3, candidatGf, electeurHj);
			election2.getVotes().add(vote);
			vote = new Vote(4, candidatBd, electeurMf);
			election2.getVotes().add(vote);
			vote = new Vote(5, candidatBp, electeurMb);
			election2.getVotes().add(vote);
			vote = new Vote(6, candidatMt, electeurGj);
			election2.getVotes().add(vote);
			vote = new Vote(6, candidatMt, electeurGj);
			election2.getVotes().add(vote);
			vote = new Vote(6, candidatMt, electeurGj);
			election2.getVotes().add(vote);
			vote = new Vote(6, candidatMt, electeurGj);
			election2.getVotes().add(vote);
			
			elections.add(election);
			elections.add(election2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return elections;
	}
	
	public static void main(String[] args) {
//		try {
//			Properties prop = new Properties();
//			prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//			Context context = new InitialContext(prop);
//			IElectionsRemote elections = (IElectionsRemote) context.lookup("ejb:elections_wildfly/ElectionsBean!heig.metier.session.IElectionsRemote");
//			IElectionsRemote elections = lookupIElectionsRemote();
//			List<Election> electionsList = elections.getElections();
//			for (Election el : electionsList) {
//				System.out.println("Election : " + el);
//			}
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (PersistException e) {
//			e.printStackTrace();
//		}
//		new ElectionsFrame(electionsList);
		new ElectionsFrame(getElections());
	}
	
    @SuppressWarnings("unused")
	private static IElectionsRemote lookupIElectionsRemote() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        // The app name is the application name of the deployed EJBs. This is typically the ear name
        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
        // EJB deployment on the server.
        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
        final String appName = "";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "elections_wildfly";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = IElectionsRemote.class.getSimpleName();
        // the remote view fully qualified class name
        final String viewClassName = IElectionsRemote.class.getName();
        // let's do the lookup
        System.out.println("Lookup => " + "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
        return (IElectionsRemote) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
    }
}
    