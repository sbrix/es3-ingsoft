package it.unipr.sbrix.esercizio1;

import java.io.Serializable;

public class Vendite implements Serializable {

	@Override
	public String toString() {
		return "Vendite [id=" + id + ", cliente=" + cliente.toString() + ", hotel="
				+ hotel.toString() + ", giorniPernottamento=" + giorniPernottamento
				+ ", andata=" + andata.toString() + ", ritorno=" + ritorno.toString()
				+ ", operatore=" + operatore.toString() + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 2808661019581646478L;
	int id;
	Cliente cliente;
	Hotel hotel;
	int giorniPernottamento;
	Volo andata,ritorno;
	Operatore operatore;

}
