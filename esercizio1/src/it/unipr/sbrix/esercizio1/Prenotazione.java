package it.unipr.sbrix.esercizio1;

import java.io.Serializable;



public class Prenotazione implements Serializable{
	@Override
	public String toString() {
		return "Prenotazione [id=" + id + ", cliente=" + cliente.toString() + ", hotel="
				+ hotel.toString() + ", giorniPernottamento=" + giorniPernottamento
				+ ", andata=" + andata.toString() + ", ritorno=" + ritorno.toString()
				+ ", operatore=" + operatore.toString() + ", scadenza=" + scadenza + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -1530929609848261724L;
	int id;
	Cliente cliente;
	Hotel hotel;
	int giorniPernottamento;
	Volo andata,ritorno;
	Operatore operatore;
	//GregorianCalendar scadenza=null;
	long scadenza=0;

}
