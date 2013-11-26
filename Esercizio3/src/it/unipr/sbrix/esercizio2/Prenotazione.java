package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Prenotazione extends ViaggioOrganizzato implements Serializable {
	public Prenotazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1530929609848261724L;

	public Utente cliente = new Utente();
	public int idOperatore = 0;
	public long scadenza = 0;

	@Override
	public String toString() {

		return "Prenotazione [id=" + id + ", cliente=" + cliente.toString()
				+ ", hotel=" + hotel.toString() + ", giorniPernottamento="
				+ durataPernottamento + ", andata=" + andata.toString()
				+ ", ritorno=" + ritorno.toString() + ", operatore="
				+ idOperatore + ", scadenza=" + scadenza + "]";
	}

}
