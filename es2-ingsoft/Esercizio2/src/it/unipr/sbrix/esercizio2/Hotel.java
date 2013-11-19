package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Hotel implements Serializable {
	@Override
	public String toString() {
		return "Hotel [nome=" + nome + ", via=" + via + ", citta=" + citta
				+ ", nazione=" + nazione + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6823533540175825734L;
	int id;
	String nome;
	String via;
	String citta;
	String nazione;

}
