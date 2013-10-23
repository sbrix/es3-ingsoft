package it.unipr.sbrix.esercizio1;

import java.io.Serializable;

public class ViaggiOrganizzati implements Serializable {
	@Override
	public String toString() {
		return "ViaggiOrganizzati [andata=" + andata.toString() + ", ritorno=" + ritorno.toString()
				+ ", hotel=" + hotel.toString() + ", durataPernottamento="
				+ durataPernottamento + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -3650137362366764777L;
	int id;
	Volo andata,ritorno;
	Hotel hotel;
	int durataPernottamento;
	

}
