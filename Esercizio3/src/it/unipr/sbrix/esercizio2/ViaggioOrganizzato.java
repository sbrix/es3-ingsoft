package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class ViaggioOrganizzato implements Serializable,
		Comparable<ViaggioOrganizzato> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3650137362366764777L;

	protected int id = 0;

	public Volo andata, ritorno;

	public Hotel hotel;

	public int durataPernottamento = 0;

	public ViaggioOrganizzato() {
		super();

		this.andata = new Volo();
		this.ritorno = new Volo();
		this.hotel = new Hotel();

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ViaggiOrganizzati [andata=" + andata.toString() + ", ritorno="
				+ ritorno.toString() + ", hotel=" + hotel.toString()
				+ ", durataPernottamento=" + durataPernottamento + "]";
	}

}
