package it.polito.tdp.lab04.model;

public class Corso {
	private String codiceInsegnamento;
	private int crediti;
	private String nome;
	private int periodoDidattico;
	
	public Corso(String codiceInsegnamento, int crediti, String nome, int periodoDidattico) {
		super();
		this.codiceInsegnamento = codiceInsegnamento;
		this.crediti = crediti;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}

	public String getCodiceInsegnamento() {
		return codiceInsegnamento;
	}

	public int getCrediti() {
		return crediti;
	}

	public String getNome() {
		return nome;
	}

	public int getPeriodoDidattico() {
		return periodoDidattico;
	}

	@Override
	public String toString() {
		return codiceInsegnamento + " " + nome;
	}
	
	
	
	

}
