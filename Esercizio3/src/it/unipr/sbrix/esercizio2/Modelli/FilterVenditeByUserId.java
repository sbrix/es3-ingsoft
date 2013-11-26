package it.unipr.sbrix.esercizio2.Modelli;

import it.unipr.sbrix.esercizio2.Vendita;

import javax.swing.RowFilter;

public class FilterVenditeByUserId extends RowFilter<ModelVendite, Integer> {
	private int userId = 0;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public boolean include(
			javax.swing.RowFilter.Entry<? extends ModelVendite, ? extends Integer> entry) {
		// TODO Auto-generated method stub
		ModelVendite model = entry.getModel();
		Vendita vendita = (Vendita) model.getItem(entry.getIdentifier());
		if (vendita.cliente.getId() == this.userId)
			return true;
		return false;
	}

}
