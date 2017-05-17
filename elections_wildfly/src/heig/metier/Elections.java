package heig.metier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Elections {
	private DataSource ds;

	public Elections(DataSource ds) {
		this.ds = ds;
	}

	public List<Electeur> getElecteurs() throws PersistException {
		ArrayList<Electeur> electeurs = new ArrayList<Electeur>();
		try {

			Connection con = ds.getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM electeurs");

			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				Electeur electeur = new Electeur(id, nom, prenom);
				electeurs.add(electeur);
			}

			rs.close();
			s.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new PersistException();
		}
		return electeurs;
	}

	public Electeur getElecteur(int electeurId) throws PersistException {
		Electeur result = null;
		try {
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM electeurs WHERE id = " + electeurId);
			if (rs.next()) {
				result = new Electeur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"));
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistException(e);
		}
		return result;
	}

	public void saveElecteur(Electeur electeur) throws DuplicateCodeException, PersistException {
		try {
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			String requete = "";
			if (electeur.getId() == 0) {
				requete = "insert into electeurs values(" + electeur.getId() + "," + "'" + electeur.getPrenom() + "',"
						+ "'" + electeur.getNom() + "')";
			} else {
				requete = "UPDATE electeurs SET nom = '" + electeur.getNom() + "'," + " prenom = '"
						+ electeur.getPrenom() + "' WHERE id = " + electeur.getId();
			}
			System.out.println(requete);
			stmt.executeUpdate(requete);
			connection.close();
		} catch (MySQLIntegrityConstraintViolationException ex) {
			throw new DuplicateCodeException(electeur.getNom());
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new PersistException();
		}
	}

	public void deleteElecteur(int id) throws PersistException {
		try {
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			String requete = "";
			if (id == 0) {
				throw new PersistException("Id invalide pour suppression.");
			}
			requete = "DELETE FROM electeurs WHERE id = " + id;
			System.out.println(requete);
			stmt.executeUpdate(requete);
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new PersistException();
		}
	}
}
