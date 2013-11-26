package it.unipr.sbrix.esercizio2.Modelli;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.event.EventListenerList;

import org.joda.time.DateTime;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Prenotazione;

@SuppressWarnings("unchecked")
public class ModelPrenotazioni extends RowTableModel<Prenotazione> implements
		EditModel, InitModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -30781717066247948L;
	private final static String[] COLUMN_NAMES = { "Id", "Partenza andata",
			"Arrivo andata", "Durata Pernottamento", "Nome hotel", "Via Hotel",
			"Città hotel", "Nazione hotel", "Partenza ritorno",
			"Arrivo ritorno", "Nome operatore", "Nome cliente", "Scadenza" };
	private ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>(
			0);
	private int idGlobalePrenotazioni = 0;
	private final File filePrenotazioni = new File(Agenzia.pathRoot
			+ "prenotazioni.dat");
	private final File fileIdPrenotazioni = new File(Agenzia.pathRoot
			+ "idPrenotazioni.dat");
	private FileInputStream prenotazioniIn = null;
	private FileInputStream idPrenotazioniIn = null;
	private ObjectInputStream objInputStream = null;
	protected static EventListenerList listenerList = new EventListenerList();
	public final static long TRENTA_GIORNI_IN_MILLIS = 2592000000L;
	public final static long TRE_GIORNI_IN_MILLIS = 259200000L;

	public ModelPrenotazioni() {
		super(Arrays.asList(COLUMN_NAMES));
		setRowClass(Prenotazione.class);
		initFromFile();
		initModel();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void addItem(Object item) {
		// TODO Auto-generated method stub
		Prenotazione prenotazione = (Prenotazione) item;
		prenotazione.setId(getNewId());
		prenotazione.scadenza = Calendar.getInstance().getTimeInMillis()
				+ ModelPrenotazioni.TRENTA_GIORNI_IN_MILLIS;
		listaPrenotazioni.add(prenotazione);
		Agenzia.saveToFile(filePrenotazioni, listaPrenotazioni);
		Agenzia.saveToFile(fileIdPrenotazioni, this.idGlobalePrenotazioni);
		fireUpdateEvent(new ModelEvent(this));

	}

	public synchronized void addUpdateEventListener(ModelListener listener) {
		listenerList.add(ModelListener.class, listener);
	}

	private synchronized void controllaScadenzaPrenotazioni() {

		// Controllo scadenza prenotazioni
		int indice = 0;
		boolean flag = false;

		for (Prenotazione i : listaPrenotazioni) {

			if (i.scadenza < (Calendar.getInstance().getTimeInMillis())) {
				listaPrenotazioni.remove(indice);
				flag = true;
				// break;
				// controllaScadenzaPrenotazioni();

			}
			if (flag)
				break;
			indice++;

		}
		// listaPrenotazioni = (ArrayList<Prenotazione>) listaTemp.clone();
		Agenzia.saveToFile(filePrenotazioni, listaPrenotazioni);
		if (flag)
			controllaScadenzaPrenotazioni();
	}

	private void fireUpdateEvent(ModelEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		System.out.println("update prenotazioni");
		for (int i = 0; i < listeners.length; i = i + 2) {
			if (listeners[i] == ModelListener.class) {
				((ModelListener) listeners[i + 1]).updateEventOccurred(evt);
			}
		}
	}

	@Override
	public synchronized Object getItem(int id) {
		// TODO Auto-generated method stub
		for (Prenotazione i : listaPrenotazioni) {
			if (i.getId() == id)
				return i;
		}
		return null;
	}

	private synchronized int getNewId() {
		return idGlobalePrenotazioni++;
	}

	@Override
	public synchronized Object getValueAt(int rowIndex, int columnIndex) {
		Prenotazione prenotazione = getRow(rowIndex);
		switch (columnIndex) {
		case 0:
			return prenotazione.getId();
		case 1:
			return prenotazione.andata.partenza;
		case 2:
			return prenotazione.andata.destinazione;
		case 3:
			return prenotazione.durataPernottamento;
		case 4:
			return prenotazione.hotel.nome;
		case 5:
			return prenotazione.hotel.via;
		case 6:
			return prenotazione.hotel.citta;
		case 7:
			return prenotazione.hotel.nazione;
		case 8:
			return prenotazione.ritorno.partenza;
		case 9:
			return prenotazione.ritorno.destinazione;
		case 10:
			return prenotazione.idOperatore;
		case 11:
			return new String(prenotazione.cliente.nome + " "
					+ prenotazione.cliente.cognome);
		case 12: {
			DateTime dataScadenza = new DateTime(prenotazione.scadenza);
			return new String(dataScadenza.getDayOfMonth() + "/"
					+ dataScadenza.getMonthOfYear() + "/"
					+ dataScadenza.getYear());

		}

		}
		return null;
	}

	@Override
	public synchronized void initFromFile() {
		// TODO Auto-generated method stub
		if (!filePrenotazioni.exists()) {

			try {
				filePrenotazioni.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (!fileIdPrenotazioni.exists()) {
			idGlobalePrenotazioni = 0;
			try {
				fileIdPrenotazioni.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			prenotazioniIn = new FileInputStream(filePrenotazioni);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			idPrenotazioniIn = new FileInputStream(fileIdPrenotazioni);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			objInputStream = new ObjectInputStream(prenotazioniIn);
			listaPrenotazioni = (ArrayList<Prenotazione>) objInputStream
					.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file prenotazioni vuoto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			objInputStream = new ObjectInputStream(idPrenotazioniIn);
			idGlobalePrenotazioni = (int) objInputStream.readObject();

			objInputStream.close();
		} catch (EOFException e) {
			idGlobalePrenotazioni = 0;
			System.out.println("file id prenotazioni vuoto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controllaScadenzaPrenotazioni();
		Collections.sort(listaPrenotazioni);
		int index = 0;
		for (Prenotazione i : listaPrenotazioni) {
			i.setId(index);
			index++;
		}
		this.idGlobalePrenotazioni = index;
		Agenzia.saveToFile(filePrenotazioni, listaPrenotazioni);
		Agenzia.saveToFile(fileIdPrenotazioni, this.idGlobalePrenotazioni);

	}

	@Override
	public synchronized void initModel() {
		// TODO Auto-generated method stub
		if (this.getRowCount() > 0) {

			this.removeRowRange(0, this.getRowCount() - 1);

		}
		for (Prenotazione i : listaPrenotazioni) {
			addRow(i);
		}

	}

	@Override
	public synchronized void removeItem(int id, int row) {
		// TODO Auto-generated method stub
		int index = 0;
		for (Prenotazione i : listaPrenotazioni) {
			if (i.getId() == id) {
				listaPrenotazioni.remove(index);
				Agenzia.saveToFile(filePrenotazioni, listaPrenotazioni);
				removeRowRange(row, row);
				fireUpdateEvent(new ModelEvent(this));
				break;
			}
			index++;
		}

	}

	public synchronized void removeMyEventListener(ModelListener listener) {
		listenerList.remove(ModelListener.class, listener);
	}

}
