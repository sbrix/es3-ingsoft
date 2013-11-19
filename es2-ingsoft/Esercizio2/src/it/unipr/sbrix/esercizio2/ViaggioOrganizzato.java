package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class ViaggioOrganizzato implements Serializable {
	@Override
	public String toString() {
		return "ViaggiOrganizzati [andata=" + andata.toString() + ", ritorno="
				+ ritorno.toString() + ", hotel=" + hotel.toString()
				+ ", durataPernottamento=" + durataPernottamento + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3650137362366764777L;

	Volo andata, ritorno;
	Hotel hotel;
	int durataPernottamento;

}
