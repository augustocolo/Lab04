package it.polito.tdp.lab04.DAO;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		System.out.println(cdao.getTuttiICorsi());
		System.out.println(cdao.getCorso("01KSUPG"));
		System.out.println("01KSUPG");
		
		
	}

}
