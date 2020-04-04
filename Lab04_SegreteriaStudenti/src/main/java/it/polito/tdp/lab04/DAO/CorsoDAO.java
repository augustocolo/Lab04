package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new ArrayList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				corsi.add(new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd")));
			}

			conn.close();
			
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codiceInsegnamento) {
		final String sql ="SELECT * FROM corso WHERE codins = ?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codiceInsegnamento);
			
			ResultSet rs = st.executeQuery();
			
			rs.next();
			
			if (rs.getString("codins") != null) {
				Corso res = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				conn.close();
				return res;
			} else {
				conn.close();
				return null;
			}
		}
		catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(String codiceInsegnamento) {
		final String sql ="SELECT s.matricola, s.cognome, s.nome, s.cds FROM studente s, iscrizione i WHERE i.codins = ? AND i.matricola = s.matricola";
		List<Studente> studenti = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codiceInsegnamento);
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()){
				studenti.add(new Studente(rs.getInt("s.matricola"), rs.getString("s.cognome"), rs.getString("s.nome"), rs.getString("s.cds")));
			}
			conn.close();
			return studenti;
			
		}
		catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(int matricola, String codiceInsegnamento) {
		// ritorna true se l'iscrizione e' avvenuta con successo
		
		final String sql ="INSERT INTO iscrizione (matricola, codins) VALUES (?, ?)";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(2, codiceInsegnamento);
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			conn.close();
			
		}
		catch (SQLException e) {
			return false;
		}
		
		return this.studenteIscrittoAlCorso(matricola, codiceInsegnamento);

	}
	
	public boolean studenteIscrittoAlCorso(int matricola, String codiceInsegnamento) {
		final String sql ="SELECT COUNT(*) FROM iscrizione WHERE matricola = ? AND codins = ?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			st.setString(2, codiceInsegnamento);
			
			ResultSet rs = st.executeQuery();
			rs.next();
			int aaa = rs.getInt("count(*)");
					
			if (aaa == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}

	}

}
