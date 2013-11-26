package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Hotel implements Serializable, Comparable<Hotel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6823533540175825734L;

	private int id;
	public String nome;

	public String via;

	public String citta;
	public String nazione;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Hotel [nome=" + nome + ", via=" + via + ", citta=" + citta
				+ ", nazione=" + nazione + "]";
	}

	@Override
	public int compareTo(Hotel o) {
		// TODO Auto-generated method stub
		if (this.id > o.getId())
			return 1;
		if (this.id == o.getId())
			return 0;
		if (this.id < o.getId())
			return -1;
		return 0;
	}

}
