package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	public Studente getStudente(int matricola) {
		final String sql ="SELECT * FROM studente WHERE matricola = ?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			
			rs.next();
			
			if (rs.getString("matricola") != null) {
				Studente res = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("cds"));
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
	
	public List<Corso> getCorsiStudente(int matricola){
		final String sql ="SELECT c.codins, c.crediti, c.nome, c.pd FROM corso c, iscrizione i WHERE i.matricola = ? AND c.codins = i.codins";
		
		List<Corso> corsi = new ArrayList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()){
				corsi.add(new Corso(rs.getString("c.codins"), rs.getInt("c.crediti"), rs.getString("c.nome"), rs.getInt("c.pd")));
			}
			conn.close();
			return corsi;
			
		}
		catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	

}
