package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class ViaggioOrganizzato implements Serializable,
		Comparable<ViaggioOrganizzato> {
	public ViaggioOrganizzato() {
		super();

		this.andata = new Volo();
		this.ritorno = new Volo();
		this.hotel = new Hotel();

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3650137362366764777L;

	protected int id = 0;

	public void setId(int id) {
		this.id = id;
	}

	public Volo andata, ritorno;

	public Hotel hotel;

	public int durataPernottamento = 0;

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ViaggiOrganizzati [andata=" + andata.toString() + ", ritorno="
				+ ritorno.toString() + ", hotel=" + hotel.toString()
				+ ", durataPernottamento=" + durataPernottamento + "]";
	}

	@Override
	public int compareTo(ViaggioOrganizzato o) {
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
