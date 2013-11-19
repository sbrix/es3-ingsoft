/**
 * 
 */
package it.unipr.sbrix.esercizio2.Modelli;

import java.lang.reflect.Array;
import java.util.Arrays;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Utente;

/**
 * @author Luca
 * 
 */
@SuppressWarnings("unchecked")
public class ModelUtenti extends RowTableModel<Utente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7685749531695911088L;
	private static String[] COLUMN_NAMES = { "Id", "Nome", "Cognome",
			"Username", "Tipo" };
	private Agenzia ag = null;
	private int type;
	static public final int INIT_UTENTE = 0;
	static public final int INIT_CLIENTE = 1;

	public ModelUtenti(Agenzia agenzia, int tipo) {

		super(Arrays.asList(COLUMN_NAMES));
		ag = agenzia;
		setRowClass(Utente.class);
		setColumnClass(1, Integer.class);
		setColumnClass(2, String.class);
		setColumnClass(3, String.class);
		setColumnClass(4, String.class);
		type = tipo;
		this.initModel(tipo);

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Utente utente = getRow(rowIndex);
		switch (columnIndex) {
		case 0:
			return utente.getId();
		case 1:
			return utente.nome;
		case 2:
			return utente.cognome;
		case 3:
			return utente.userName;
		case 4: {
			if (utente.getUserType() == Utente.ADMIN)
				return "Admin";
			if (utente.getUserType() == Utente.CLIENTE)
				return "Cliente";
			if (utente.getUserType() == Utente.OPERATORE)
				return "Operatore";

		}
		default:
			return null;
		}
	}

	public void initModel(int tipoLista) {

		if (this.getRowCount() > 0) {

			this.removeRows(this.getRowCount() - 1);

		}
		if (type == ModelUtenti.INIT_UTENTE) {
			for (Utente i : ag.listaUtenti) {
				this.addRow(i);
			}

		}
		if (type == ModelUtenti.INIT_CLIENTE) {
			for (Utente i : ag.listaUtenti) {
				if (i.getUserType() == Utente.CLIENTE)
					this.addRow(i);

			}

		}

	}
}
