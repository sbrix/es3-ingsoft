package it.unipr.sbrix.esercizio2.Modelli;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.event.EventListenerList;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Vendita;

@SuppressWarnings("unchecked")
public class ModelVendite extends RowTableModel<Vendita> implements EditModel,
		InitModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3393649562267312023L;
	private final static String[] COLUMN_NAMES = { "Id", "Partenza andata",
			"Arrivo andata", "Durata Pernottamento", "Nome hotel", "Via Hotel",
			"Città hotel", "Nazione hotel", "Partenza ritorno",
			"Arrivo ritorno", "Nome operatore", "Nome cliente" };
	private ArrayList<Vendita> listaVendite = new ArrayList<Vendita>(0);
	private int idGlobaleVendite = 0;
	private final File fileVendite = new File(Agenzia.pathRoot + "vendite.dat");
	private final File fileIdVendite = new File(Agenzia.pathRoot
			+ "idVendite.dat");
	private FileInputStream venditeIn = null;
	private FileInputStream idVenditeIn = null;
	private ObjectInputStream objInputStream = null;
	protected static EventListenerList listenerList = new EventListenerList();

	public ModelVendite() {
		super(Arrays.asList(COLUMN_NAMES));
		setRowClass(Vendita.class);
		initFromFile();
		initModel();

	}

	private synchronized int getNewId() {
		return this.idGlobaleVendite++;
	}

	@Override
	public synchronized Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Vendita vendita = getRow(rowIndex);
		switch (columnIndex) {
		case 0:
			return vendita.getId();
		case 1:
			return vendita.andata.partenza;
		case 2:
			return vendita.andata.destinazione;
		case 3:
			return vendita.durataPernottamento;
		case 4:
			return vendita.hotel.nome;
		case 5:
			return vendita.hotel.via;
		case 6:
			return vendita.hotel.citta;
		case 7:
			return vendita.hotel.nazione;
		case 8:
			return vendita.ritorno.partenza;
		case 9:
			return vendita.ritorno.destinazione;
		case 10:
			return vendita.idOperatore;
		case 11:
			return vendita.cliente.toString();

		}
		return null;
	}

	@Override
	public synchronized void initFromFile() {
		// TODO Auto-generated method stub
		if (!fileVendite.exists()) {

			try {
				fileVendite.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (!fileIdVendite.exists()) {
			idGlobaleVendite = 0;
			try {
				fileIdVendite.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			venditeIn = new FileInputStream(fileVendite);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			idVenditeIn = new FileInputStream(fileIdVendite);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			objInputStream = new ObjectInputStream(venditeIn);
			listaVendite = (ArrayList<Vendita>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file vendite vuoto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			objInputStream = new ObjectInputStream(idVenditeIn);
			idGlobaleVendite = (int) objInputStream.readObject();

			objInputStream.close();
		} catch (EOFException e) {
			idGlobaleVendite = 0;
			System.out.println("file id vendite vuoto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(listaVendite);
		int index = 0;
		for (Vendita i : listaVendite) {
			i.setId(index);
			index++;
		}
		this.idGlobaleVendite = index;
		Agenzia.saveToFile(fileVendite, listaVendite);
		Agenzia.saveToFile(fileIdVendite, this.idGlobaleVendite);

	}

	@Override
	public synchronized void initModel() {
		// TODO Auto-generated method stub
		if (this.getRowCount() > 0) {

			this.removeRowRange(0, this.getRowCount() - 1);

		}
		for (Vendita i : listaVendite) {
			addRow(i);
		}

	}

	@Override
	public synchronized void addItem(Object item) {
		// TODO Auto-generated method stub
		Vendita vendita = (Vendita) item;
		vendita.setId(getNewId());
		listaVendite.add(vendita);
		Agenzia.saveToFile(fileVendite, listaVendite);
		Agenzia.saveToFile(fileIdVendite, this.idGlobaleVendite);
		fireUpdateEvent(new ModelEvent(this));

	}

	@Override
	public synchronized Object getItem(int id) {
		// TODO Auto-generated method stub
		for (Vendita i : listaVendite) {
			if (i.getId() == id)
				return i;
		}
		return null;
	}

	@Override
	public synchronized void removeItem(int id, int row) {
		// TODO Auto-generated method stub
		int index = 0;
		for (Vendita i : listaVendite) {
			if (i.getId() == id) {
				listaVendite.remove(index);
				Agenzia.saveToFile(fileVendite, listaVendite);
				removeRowRange(row, row);
				fireUpdateEvent(new ModelEvent(this));
				break;
			}
			index++;
		}

	}

	public synchronized void addUpdateEventListener(ModelListener listener) {
		listenerList.add(ModelListener.class, listener);
	}

	public synchronized void removeMyEventListener(ModelListener listener) {
		listenerList.remove(ModelListener.class, listener);
	}

	private void fireUpdateEvent(ModelEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		System.out.println("update vendite");
		for (int i = 0; i < listeners.length; i = i + 2) {
			if (listeners[i] == ModelListener.class) {
				((ModelListener) listeners[i + 1]).updateEventOccurred(evt);
			}
		}
	}

}
