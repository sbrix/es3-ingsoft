package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Vendita extends ViaggioOrganizzato implements Serializable {

	public Vendita() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2808661019581646478L;

	public Utente cliente = new Utente();
	public int idOperatore = 0;

	@Override
	public String toString() {
		return "Vendite [id=" + id + ", cliente=" + cliente.toString()
				+ ", hotel=" + hotel.toString() + ", giorniPernottamento="
				+ durataPernottamento + ", andata=" + andata.toString()
				+ ", ritorno=" + ritorno.toString() + ", operatore="
				+ idOperatore + "]";
	}

}
