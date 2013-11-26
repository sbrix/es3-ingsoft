package it.unipr.sbrix.esercizio2.Modelli;

public interface EditModel {
	void addItem(Object item);

	Object getItem(int id);

	void removeItem(int id, int row);

}
