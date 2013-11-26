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
import it.unipr.sbrix.esercizio2.ViaggioOrganizzato;

@SuppressWarnings("unchecked")
public class ModelViaggiOrganizzati extends RowTableModel<ViaggioOrganizzato>
		implements EditModel, InitModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2251662406643181544L;
	private final static String[] COLUMN_NAMES = { "Id", "Partenza andata",
			"Arrivo andata", "Durata Pernottamento", "Nome hotel", "Via Hotel",
			"Città hotel", "Nazione hotel", "Partenza ritorno",
			"Arrivo ritorno" };
	private ArrayList<ViaggioOrganizzato> listaViaggiOrganizzati = new ArrayList<ViaggioOrganizzato>(
			0);
	private int idGlobaleViaggiOrganizzati = 0;
	private final File fileViaggiOrganizzati = new File(Agenzia.pathRoot
			+ "viaggi.dat");
	private final File fileIdViaggiOrganizzati = new File(Agenzia.pathRoot
			+ "idViaggiOrg.dat");
	private FileInputStream viaggiIn = null;
	private FileInputStream idViaggiOrgIn = null;
	private ObjectInputStream objInputStream = null;
	protected static EventListenerList listenerList = new EventListenerList();

	public ModelViaggiOrganizzati() {
		super(Arrays.asList(COLUMN_NAMES));
		setRowClass(ViaggioOrganizzato.class);
		initFromFile();
		initModel();
		// TODO Auto-generated constructor stub
	}

	private synchronized int getNewId() {
		return idGlobaleViaggiOrganizzati++;
	}

	@Override
	public synchronized Object getValueAt(int rowIndex, int columnIndex) {
		ViaggioOrganizzato viaggio = getRow(rowIndex);
		switch (columnIndex) {
		case 0:
			return viaggio.getId();
		case 1:
			return viaggio.andata.partenza;
		case 2:
			return viaggio.andata.destinazione;
		case 3:
			return viaggio.durataPernottamento;
		case 4:
			return viaggio.hotel.nome;
		case 5:
			return viaggio.hotel.via;
		case 6:
			return viaggio.hotel.citta;
		case 7:
			return viaggio.hotel.nazione;
		case 8:
			return viaggio.ritorno.partenza;
		case 9:
			return viaggio.ritorno.destinazione;
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void initFromFile() {
		// TODO Auto-generated method stub
		if (!fileViaggiOrganizzati.exists()) {

			try {
				fileViaggiOrganizzati.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!fileIdViaggiOrganizzati.exists()) {
			idGlobaleViaggiOrganizzati = 0;
			try {
				fileIdViaggiOrganizzati.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			viaggiIn = new FileInputStream(fileViaggiOrganizzati);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			idViaggiOrgIn = new FileInputStream(fileIdViaggiOrganizzati);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			objInputStream = new ObjectInputStream(viaggiIn);
			listaViaggiOrganizzati = (ArrayList<ViaggioOrganizzato>) objInputStream
					.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file viaggi organizzati vuoto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			objInputStream = new ObjectInputStream(idViaggiOrgIn);
			idGlobaleViaggiOrganizzati = (int) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			idGlobaleViaggiOrganizzati = 0;
			System.out.println("file id viaggi organizzati vuoto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(listaViaggiOrganizzati);
		int index = 0;
		for (ViaggioOrganizzato i : listaViaggiOrganizzati) {
			i.setId(index);
			index++;
		}
		this.idGlobaleViaggiOrganizzati = index;
		Agenzia.saveToFile(fileViaggiOrganizzati, listaViaggiOrganizzati);
		Agenzia.saveToFile(fileIdViaggiOrganizzati,
				this.idGlobaleViaggiOrganizzati);

	}

	@Override
	public synchronized void initModel() {
		// TODO Auto-generated method stub
		if (this.getRowCount() > 0) {

			this.removeRowRange(0, this.getRowCount() - 1);

		}
		for (ViaggioOrganizzato i : listaViaggiOrganizzati) {
			addRow(i);
		}

	}

	@Override
	public synchronized void addItem(Object item) {
		// TODO Auto-generated method stub
		ViaggioOrganizzato viaggio = (ViaggioOrganizzato) item;
		viaggio.setId(getNewId());
		listaViaggiOrganizzati.add(viaggio);
		addRow(viaggio);
		Agenzia.saveToFile(fileViaggiOrganizzati, listaViaggiOrganizzati);
		Agenzia.saveToFile(fileIdViaggiOrganizzati,
				this.idGlobaleViaggiOrganizzati);
		fireUpdateEvent(new ModelEvent(this));

	}

	@Override
	public synchronized Object getItem(int id) {
		// TODO Auto-generated method stub
		for (ViaggioOrganizzato i : listaViaggiOrganizzati) {
			if (i.getId() == id)
				return i;
		}
		return null;
	}

	@Override
	public synchronized void removeItem(int id, int row) {
		// TODO Auto-generated method stub
		int index = 0;
		for (ViaggioOrganizzato i : listaViaggiOrganizzati) {
			if (i.getId() == id) {
				listaViaggiOrganizzati.remove(index);
				Agenzia.saveToFile(fileViaggiOrganizzati,
						listaViaggiOrganizzati);
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
		System.out.println("update viaggi organizzati");
		for (int i = 0; i < listeners.length; i = i + 2) {
			if (listeners[i] == ModelListener.class) {
				((ModelListener) listeners[i + 1]).updateEventOccurred(evt);
			}
		}
	}

}
