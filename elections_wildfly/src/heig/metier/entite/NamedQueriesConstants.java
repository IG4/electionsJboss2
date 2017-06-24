package heig.metier.entite;

public class NamedQueriesConstants {
	
	public static final String CANDIDATE_BY_ID_QUERY = "SELECT c FROM Candidat c WHERE c.id = :id";
	public static final String CANDIDATE_BY_ID_QUERY_PARAM = "id";
	public static final String CANDIDATE_BY_ID_QUERY_NAME = "find candidate by id";

	public static final String CANDIDATE_LIST_QUERY = "SELECT c FROM Candidat c";
	public static final String CANDIDATE_LIST_QUERY_NAME = "find all candidates";
	
	public static final String ELECTION_BY_ID_QUERY = "SELECT e FROM Election e WHERE e.id = :id";
	public static final String ELECTION_BY_ID_QUERY_PARAM = "id";
	public static final String ELECTION_BY_ID_QUERY_NAME = "find election by id";
	
	public static final String ELECTION_LIST_QUERY = "SELECT e FROM Election e";
	public static final String ELECTION_LIST_QUERY_NAME = "find all elections";
	
	public static final String ELECTOR_BY_ID_QUERY = "SELECT e FROM Electeur e WHERE e.id = :id";
	public static final String ELECTOR_BY_ID_QUERY_PARAM = "id";
	public static final String ELECTOR_BY_ID_QUERY_NAME = "find elector by id";
	
	public static final String ELECTOR_LIST_QUERY = "SELECT e FROM Electeur e";
	public static final String ELECTOR_LIST_QUERY_NAME = "find all electors";
	
	public static final String PARTI_BY_ID_QUERY = "SELECT p FROM Parti p WHERE p.id = :id";
	public static final String PARTI_BY_ID_QUERY_PARAM = "id";
	public static final String PARTI_BY_ID_QUERY_NAME = "find parti by id";
	
	public static final String PARTI_BY_NAME_QUERY = "SELECT p FROM Parti p WHERE p.nom = :nom";
	public static final String PARTI_BY_NAME_QUERY_NAME = "find parti by name";
	public static final String PARTI_BY_NAME_QUERY_PARAM = "nom";
	
	public static final String PARTI_LIST_QUERY = "SELECT p FROM Parti p";
	public static final String PARTI_LIST_QUERY_NAME = "find all partis";
}
