/**
 * 
 */
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
import it.unipr.sbrix.esercizio2.Utente;

/**
 * @author Luca
 * 
 */
@SuppressWarnings("unchecked")
public class ModelUtenti extends RowTableModel<Utente> implements InitModel,
		EditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7685749531695911088L;
	private final static String[] COLUMN_NAMES = { "Id", "Nome", "Cognome",
			"Username", "Tipo" };
	private ArrayList<Utente> listaUtenti = new ArrayList<Utente>(0);
	private int idGlobaleUtenti = 0;
	public final File fileUtenti = new File(Agenzia.pathRoot + "utenti.dat");
	public final File fileIdUtenti = new File(Agenzia.pathRoot + "idUtenti.dat");
	private FileInputStream utentiIn = null;
	private FileInputStream idUtentiIn = null;

	protected static EventListenerList listenerList = new EventListenerList();

	private int type;
	static public final int INIT_UTENTE = 0;
	static public final int INIT_CLIENTE = 1;

	public ModelUtenti(int tipo) {

		super(Arrays.asList(COLUMN_NAMES));

		setRowClass(Utente.class);
		/*
		 * setColumnClass(0, Integer.class); setColumnClass(1, String.class);
		 * setColumnClass(2, String.class); setColumnClass(3, String.class);
		 * setColumnClass(4, String.class);
		 */
		type = tipo;
		this.initFromFile();
		this.initModel();

	}

	@Override
	public synchronized void addItem(Object item) {
		// TODO Auto-generated method stub
		// inserire codice aggiunta oggetto
		Utente utente = (Utente) item;
		utente.setId(getNewID());
		listaUtenti.add(utente);
		Agenzia.saveToFile(fileIdUtenti, idGlobaleUtenti);
		Agenzia.saveToFile(fileUtenti, listaUtenti);
		addRow(utente);
		this.fireUpdateEvent(new ModelEvent(this));

	}

	public synchronized void addUpdateEventListener(ModelListener l) {
		listenerList.add(ModelListener.class, l);
	}

	// Notify all listeners that have registered interest for
	// notification on this event type. The event instance
	// is lazily created using the parameters passed into
	// the fire method.

	public synchronized int checkUserLogin(String username, String password) {
		for (Utente i : listaUtenti) {
			if (i.userName.equals(username)
					&& Agenzia.passwordEncryptor.checkPassword(password,
							i.password))
				return i.getId();
		}
		return -1;

	}

	protected void fireUpdateEvent(ModelEvent evt) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == ModelListener.class) {
				// Lazily create the event:

				((ModelListener) listeners[i + 1]).updateEventOccurred(evt);
			}
		}
	}

	@Override
	public synchronized final Object getItem(int id) {
		// TODO Auto-generated method stub
		for (Utente i : listaUtenti) {
			if (i.getId() == id)
				return i;
		}
		return null;
	}

	private synchronized int getNewID() {
		// TODO Auto-generated method stub
		return idGlobaleUtenti++;
	}

	@Override
	public synchronized Object getValueAt(int rowIndex, int columnIndex) {

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

	@Override
	public synchronized void initFromFile() {
		// TODO Auto-generated method stub
		if (!fileUtenti.exists()) {

			try {
				fileUtenti.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!fileIdUtenti.exists()) {
			try {
				fileIdUtenti.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			idGlobaleUtenti = 0;
		}

		try {
			utentiIn = new FileInputStream(fileUtenti);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			idUtentiIn = new FileInputStream(fileIdUtenti);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ObjectInputStream objInputStream = new ObjectInputStream(utentiIn);
			listaUtenti = (ArrayList<Utente>) objInputStream.readObject();
			boolean adminFound = false;
			for (Utente i : listaUtenti) {
				if (i.getUserType() == Utente.ADMIN) {
					adminFound = true;
					break;
				}
			}
			if (!adminFound) {
				Utente admin = new Utente("admin", "admin", "admin", "admin");
				admin.setUserType(Utente.ADMIN);
				listaUtenti.add(admin);
				Agenzia.saveToFile(fileUtenti, listaUtenti);
				System.out.println("Utente admin creato");

			}
			objInputStream.close();
		} catch (EOFException e) {
			// System.out.println("file clienti vuoto");
			// se lista vuota devo creare l accound admin di default
			Utente admin = new Utente("admin", "admin", "admin", "admin");
			admin.setUserType(Utente.ADMIN);
			admin.setId(0);
			listaUtenti.add(admin);
			Agenzia.saveToFile(fileUtenti, listaUtenti);
			System.out.println("Utente admin creato");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ObjectInputStream objInputStream = new ObjectInputStream(idUtentiIn);
			idGlobaleUtenti = (int) objInputStream.readObject();

			objInputStream.close();
		} catch (EOFException e) {
			idGlobaleUtenti = 0;
			Agenzia.saveToFile(fileIdUtenti, idGlobaleUtenti);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(this.listaUtenti);
		int index = 0;
		for (Utente i : listaUtenti) {
			i.setId(index);
			index++;
		}
		this.idGlobaleUtenti = index;
		Agenzia.saveToFile(fileUtenti, listaUtenti);
		Agenzia.saveToFile(fileIdUtenti, this.idGlobaleUtenti);

	}

	public synchronized void initModel() {

		if (this.getRowCount() > 0) {

			this.removeRowRange(0, this.getRowCount() - 1);

		}
		if (type == ModelUtenti.INIT_UTENTE) {
			for (Utente i : listaUtenti) {
				this.addRow(i);
			}

		}
		if (type == ModelUtenti.INIT_CLIENTE) {
			for (Utente i : listaUtenti) {
				if (i.getUserType() == Utente.CLIENTE)
					this.addRow(i);

			}

		}

	}

	@Override
	public synchronized void removeItem(int id, int row) {
		// TODO Auto-generated method stub

		int index = 0;
		for (Utente i : listaUtenti) {

			if (i.getId() == id) {
				listaUtenti.remove(index);
				Agenzia.saveToFile(fileUtenti, listaUtenti);
				removeRowRange(row, row);
				this.fireUpdateEvent(new ModelEvent(this));
				break;

			}
			index++;

		}

	}

	public synchronized void removeUpdateEventListener(ModelListener l) {
		listenerList.remove(ModelListener.class, l);
	}
}
