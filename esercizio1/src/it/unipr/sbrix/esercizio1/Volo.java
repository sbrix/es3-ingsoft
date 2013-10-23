package it.unipr.sbrix.esercizio1;

import java.io.Serializable;

public class Volo implements Serializable{
	@Override
	public String toString() {
		return "Volo [partenza=" + partenza + ", destinazione=" + destinazione
				+ "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -5381232667756946635L;
	int id;
	String partenza;
	String destinazione;

}
