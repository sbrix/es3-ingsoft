package it.unipr.sbrix.esercizio2.Modelli;

import it.unipr.sbrix.esercizio2.Prenotazione;

import javax.swing.RowFilter;

public class FilterPrenotazioniByUserId extends
		RowFilter<ModelPrenotazioni, Integer> {

	private int userId = 0;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public boolean include(
			javax.swing.RowFilter.Entry<? extends ModelPrenotazioni, ? extends Integer> entry) {
		// TODO Auto-generated method stub

		ModelPrenotazioni model = entry.getModel();
		if (model == null)
			System.out.println("modello prenotazione vuoto");
		System.out.println("entry identifier:" + entry.getIdentifier());
		Prenotazione prenotazione = (Prenotazione) model.getItem(entry
				.getIdentifier());
		if (prenotazione != null && prenotazione.cliente.getId() == this.userId)
			return true;
		// if(prenotazione.cliente.getId()==entry.getIdentifier()) return true;
		System.out.println("oggetto prenotazione vuoto");
		return false;
	}

}
