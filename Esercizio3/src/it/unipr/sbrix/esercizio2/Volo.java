package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Volo implements Serializable, Comparable<Volo> {
	/**
	 * 
	 */

	private static final long serialVersionUID = -5381232667756946635L;

	private int id;
	public String partenza;

	public String destinazione;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Volo [partenza=" + partenza + ", destinazione=" + destinazione
				+ "]";
	}

	@Override
	public int compareTo(Volo o) {
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
