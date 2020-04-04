package it.polito.tdp.lab04.model;

public class Studente {
	
	private int matricola;
	private String cognome;
	private String nome;
	private String corsoDiStudi;
	public Studente(int matricola, String cognome, String nome, String corsoDiStudi) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.corsoDiStudi = corsoDiStudi;
	}
	public int getMatricola() {
		return matricola;
	}
	public String getCognome() {
		return cognome;
	}
	public String getNome() {
		return nome;
	}
	public String getCorsoDiStudi() {
		return corsoDiStudi;
	}
	
	

}
