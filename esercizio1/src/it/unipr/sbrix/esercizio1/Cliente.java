package it.unipr.sbrix.esercizio1;

import java.io.Serializable;

public class Cliente implements Serializable {
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cognome=" + cognome
				+ "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 3705447070498719951L;
	Cliente(){};
	Cliente(String nm, String cg){
		nome=nm;
		cognome=cg;
		
	}
	Integer id=null;
	/*private String nome;
	private String cognome;
	void setNome(String var){
		this.nome=var;
	}
	void setCognome(String var){
		this.cognome=var;
	}
	String getNome(){
		return this.nome;
	};
	String getCognome(){
		return this.cognome;
	}*/
	String nome=null;
	String cognome=null;

}
