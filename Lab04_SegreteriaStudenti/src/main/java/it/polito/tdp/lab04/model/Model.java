package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	private CorsoDAO cdao = new CorsoDAO();
	private StudenteDAO sdao = new StudenteDAO();
	
	public Studente getStudente(int matricola) {
		try {
			Studente res = sdao.getStudente(matricola);
			return res;
		} catch (RuntimeException e) {
			return null;
		}
		
	}
	
	public List<Corso> getCorsi(){
		return cdao.getTuttiICorsi();
	}
	
	public List<Studente> getStudentiIscritti(Corso corso){
		return cdao.getStudentiIscrittiAlCorso(corso.getCodiceInsegnamento());
	}
	
	public List<Corso> getCorsiStudente(Studente studente){
		return sdao.getCorsiStudente(studente.getMatricola());
	}
	
	public boolean studenteIscrittoAlCorso(int matricola, Corso corso) {
		return cdao.studenteIscrittoAlCorso(matricola, corso.getCodiceInsegnamento());
	}
	
	public boolean iscriviStudente(int matricola, Corso corso) {
		//controlla che esista lo studente
		Studente s = this.getStudente(matricola);
		if ( s == null) {
			return false;
		} else {
			return cdao.iscriviStudenteACorso(matricola, corso.getCodiceInsegnamento());
		}
		
	}
} 
