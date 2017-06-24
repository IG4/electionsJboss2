package heig.metier.session;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import heig.metier.entite.Candidat;
import heig.metier.entite.Electeur;
import heig.metier.entite.Election;
import heig.metier.entite.NamedQueriesConstants;
import heig.metier.entite.Parti;
import heig.metier.entite.Vote;
import heig.metier.exceptions.PersistException;

@Singleton
@Startup
public class ElectionsDatabaseInitializerBean {

		@Inject
		private IElections elections;
		
		private void affectCandidatesToPartis() throws PersistException {
			List<Candidat> candidatsDispo = elections.getPersitableList(NamedQueriesConstants.CANDIDATE_LIST_QUERY_NAME);// 5 candidates created
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(NamedQueriesConstants.PARTI_BY_NAME_QUERY_PARAM, "PDC");
			Parti parti = (Parti) elections.getPersistable(NamedQueriesConstants.PARTI_BY_NAME_QUERY_NAME, params);
			parti.addCandidat(candidatsDispo.get(0));
			parti.addCandidat(candidatsDispo.get(1));
			elections.save(parti);
			
			params.put(NamedQueriesConstants.PARTI_BY_NAME_QUERY_PARAM, "PLR");
			parti = (Parti) elections.getPersistable(NamedQueriesConstants.PARTI_BY_NAME_QUERY_NAME, params);
			parti.addCandidat(candidatsDispo.get(2));
			parti.addCandidat(candidatsDispo.get(3));
			elections.save(parti);
			
			params.put(NamedQueriesConstants.PARTI_BY_NAME_QUERY_PARAM, "Parti de rien");
			parti = (Parti) elections.getPersistable(NamedQueriesConstants.PARTI_BY_NAME_QUERY_NAME, params);
			parti.addCandidat(candidatsDispo.get(4));
			elections.save(parti);
		}
		
		private void createElection(String code, String name, Date begin, Date end) throws PersistException {
			Election election = new Election(null, name, code, begin, end);
			// affect all candidates
			List<Candidat> candidatsDispo = elections.getPersitableList(NamedQueriesConstants.CANDIDATE_LIST_QUERY_NAME);
			for (Candidat c : candidatsDispo) {
				election.getCandidats().add(c);
			}
			// affect all electors
			List<Electeur> electeursDispo = elections.getPersitableList(NamedQueriesConstants.ELECTOR_LIST_QUERY_NAME);
			for (Electeur e : electeursDispo) {
				election.getElecteurs().add(e);
			}
			elections.save(election);
		}
		
		private void createPartis(SimpleDateFormat sdf) throws PersistException, ParseException {
			elections.save(new Parti(null, "PDC", new Date(sdf.parse("12/09/1902").getTime()), "Bern"));
			elections.save(new Parti(null, "PLR", new Date(sdf.parse("07/02/1904").getTime()), "Basel"));
			elections.save(new Parti(null, "Parti de rien", new Date(sdf.parse("05/03/2012").getTime()), "Lausanne"));
		}
		
		private void createPeople(SimpleDateFormat sdf) throws PersistException, ParseException {
			// create electors
			createPerson(true, "Doe", "John", "Lausanne", new Date(sdf.parse("12/05/1982").getTime()));
			createPerson(true, "Doe", "Jane", "Lausanne", new Date(sdf.parse("14/07/1984").getTime()));
			createPerson(true, "Hendrix", "Jimmy", "Montreux", new Date(sdf.parse("07/11/1944").getTime()));
			createPerson(true, "Mercury", "Freddy", "Montreux", new Date(sdf.parse("07/11/1952").getTime()));
			createPerson(true, "Marley", "Bob", "Zürich", new Date(sdf.parse("18/04/1947").getTime()));
			createPerson(true, "Goldmann", "Jean-Jacques", "Genève", new Date(sdf.parse("09/12/1953").getTime()));
			createPerson(true, "Hagen", "Nina", "Berlin", new Date(sdf.parse("09/12/1957").getTime()));
			createPerson(true, "Bohr", "Niels", "Copenhague", new Date(sdf.parse("09/12/1973").getTime()));
			createPerson(true, "Einstein", "Albert", "Ulm", new Date(sdf.parse("19/02/1979").getTime()));
			createPerson(true, "Majorana", "Ettore", "Catane", new Date(sdf.parse("15/06/1975").getTime()));
			createPerson(true, "Heisenberg", "Werner", "Wurtzbourg", new Date(sdf.parse("29/09/1982").getTime()));
			createPerson(true, "Gödel", "Kurt", "Vienne", new Date(sdf.parse("06/10/1984").getTime()));
			// create candidates
			createPerson(false, "Leuthard", "Doris", "Araau", new Date(sdf.parse("21/04/1969").getTime()));
			createPerson(false, "Broulis", "Pascal", "Lausanne", new Date(sdf.parse("03/10/1967").getTime()));
			createPerson(false, "Morand", "Toto", "Lausanne", new Date(sdf.parse("23/09/1957").getTime()));
			createPerson(false, "Brélaz", "Daniel", "Lausanne", new Date(sdf.parse("14/02/1962").getTime()));
			createPerson(false, "Germond", "Florence", "Lausanne", new Date(sdf.parse("14/02/1962").getTime()));
		}
		
		private void createPerson(boolean elector, String lastName, String firstName, String city, Date dob) throws PersistException {
			if (elector) {
				elections.save(new Electeur(null, lastName, firstName, dob, city));
			}
			else {
				elections.save(new Candidat(null, lastName, firstName, dob, city));
			}
		}
		
		private void createVote(Electeur electeur, Candidat candidat, Election election) throws PersistException {
			elections.save(new Vote(null, candidat, electeur, election));
		}
		
		private void createVotes() throws PersistException {
			List<Election> electionsDispo = elections.getPersitableList(NamedQueriesConstants.ELECTION_LIST_QUERY_NAME);// 3 elections created
			List<Electeur> electeursDispo = elections.getPersitableList(NamedQueriesConstants.ELECTOR_LIST_QUERY_NAME);// 12 electors created
			List<Candidat> candidatsDispo = elections.getPersitableList(NamedQueriesConstants.CANDIDATE_LIST_QUERY_NAME);// 5 candidates created
			// create votes for 1st election loaded
			createVote(electeursDispo.get(0), candidatsDispo.get(0), electionsDispo.get(0));
			createVote(electeursDispo.get(1), candidatsDispo.get(0), electionsDispo.get(0));
			createVote(electeursDispo.get(2), candidatsDispo.get(1), electionsDispo.get(0));
			createVote(electeursDispo.get(3), candidatsDispo.get(1), electionsDispo.get(0));
			createVote(electeursDispo.get(4), candidatsDispo.get(2), electionsDispo.get(0));
			createVote(electeursDispo.get(5), candidatsDispo.get(2), electionsDispo.get(0));
			createVote(electeursDispo.get(6), candidatsDispo.get(3), electionsDispo.get(0));
			createVote(electeursDispo.get(7), candidatsDispo.get(3), electionsDispo.get(0));
			createVote(electeursDispo.get(8), candidatsDispo.get(4), electionsDispo.get(0));
			createVote(electeursDispo.get(9), candidatsDispo.get(4), electionsDispo.get(0));
			createVote(electeursDispo.get(10), candidatsDispo.get(0), electionsDispo.get(0));
			createVote(electeursDispo.get(11), candidatsDispo.get(0), electionsDispo.get(0));
			// create votes for 2nd election loaded
			createVote(electeursDispo.get(0), candidatsDispo.get(0), electionsDispo.get(1));
			createVote(electeursDispo.get(1), candidatsDispo.get(1), electionsDispo.get(1));
			createVote(electeursDispo.get(2), candidatsDispo.get(1), electionsDispo.get(1));
			createVote(electeursDispo.get(3), candidatsDispo.get(1), electionsDispo.get(1));
			createVote(electeursDispo.get(4), candidatsDispo.get(2), electionsDispo.get(1));
			createVote(electeursDispo.get(5), candidatsDispo.get(2), electionsDispo.get(1));
			createVote(electeursDispo.get(6), candidatsDispo.get(3), electionsDispo.get(1));
			createVote(electeursDispo.get(7), candidatsDispo.get(3), electionsDispo.get(1));
			createVote(electeursDispo.get(8), candidatsDispo.get(4), electionsDispo.get(1));
			createVote(electeursDispo.get(9), candidatsDispo.get(4), electionsDispo.get(1));
			createVote(electeursDispo.get(10), candidatsDispo.get(2), electionsDispo.get(1));
			createVote(electeursDispo.get(11), candidatsDispo.get(2), electionsDispo.get(1));
			// create votes for 3rd election loaded
			createVote(electeursDispo.get(0), candidatsDispo.get(0), electionsDispo.get(2));
			createVote(electeursDispo.get(1), candidatsDispo.get(0), electionsDispo.get(2));
			createVote(electeursDispo.get(2), candidatsDispo.get(1), electionsDispo.get(2));
			createVote(electeursDispo.get(3), candidatsDispo.get(2), electionsDispo.get(2));
			createVote(electeursDispo.get(4), candidatsDispo.get(2), electionsDispo.get(2));
			createVote(electeursDispo.get(5), candidatsDispo.get(2), electionsDispo.get(2));
			createVote(electeursDispo.get(6), candidatsDispo.get(3), electionsDispo.get(2));
			createVote(electeursDispo.get(7), candidatsDispo.get(3), electionsDispo.get(2));
			createVote(electeursDispo.get(8), candidatsDispo.get(4), electionsDispo.get(2));
			createVote(electeursDispo.get(9), candidatsDispo.get(4), electionsDispo.get(2));
			createVote(electeursDispo.get(10), candidatsDispo.get(4), electionsDispo.get(2));
			createVote(electeursDispo.get(11), candidatsDispo.get(2), electionsDispo.get(2));
		}
		
		@PostConstruct
		public void init() {
			/**
			 * Ppoulate db with some ppl
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			try {
				createPeople(sdf);
				System.out.println("Electors and Candidates creation : OK");
				createElection("MD43", "Test43", new Date(sdf.parse("02/07/2015").getTime()), new Date(sdf.parse("14/07/2015").getTime()));
				createElection("MD44", "Test44", new Date(sdf.parse("05/05/2016").getTime()), new Date(sdf.parse("17/05/2016").getTime()));
				createElection("MD45", "Test45", new Date(sdf.parse("10/06/2017").getTime()), new Date(sdf.parse("21/06/2017").getTime()));
				System.out.println("Elections creation : OK");
				createVotes();
				System.out.println("Votes creation : OK");
				createPartis(sdf);
				affectCandidatesToPartis();
				System.out.println("Partis creation : OK");
			} catch (PersistException | ParseException e) {
				e.printStackTrace();
			}
		}
}
