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
import it.unipr.sbrix.esercizio2.Hotel;

@SuppressWarnings("unchecked")
public class ModelHotel extends RowTableModel<Hotel> implements InitModel,
		EditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2339574117133691992L;
	private final static String[] COLUMN_NAMES = { "Id", "Nome", "Via",
			"Città", "Nazione" };
	private ArrayList<Hotel> listaHotel = new ArrayList<Hotel>(0);
	private final File fileHotel = new File(Agenzia.pathRoot + "hotel.dat");
	private final File fileIdHotel = new File(Agenzia.pathRoot + "idHotel.dat");
	private FileInputStream hotelIn = null;
	private FileInputStream idHotelIn = null;
	private int idGlobaleHotel;
	protected static EventListenerList listenerList = new EventListenerList();

	private synchronized int getNewId() {
		return idGlobaleHotel++;
	}

	public ModelHotel() {
		super(Arrays.asList(COLUMN_NAMES));
		setRowClass(Hotel.class);
		initFromFile();
		initModel();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Hotel hotel = getRow(rowIndex);
		switch (columnIndex) {
		case 0:
			return hotel.getId();
		case 1:
			return hotel.nome;
		case 2:
			return hotel.via;
		case 3:
			return hotel.citta;
		case 4:
			return hotel.nazione;
		default:
			return null;
		}

	}

	@Override
	public void addItem(Object item) {
		// TODO Auto-generated method stub
		Hotel hotel = (Hotel) item;
		hotel.setId(getNewId());
		listaHotel.add(hotel);
		Agenzia.saveToFile(fileHotel, listaHotel);
		Agenzia.saveToFile(fileIdHotel, this.idGlobaleHotel);
		addRow(hotel);
		fireUpdateEvent(new ModelEvent(this));

	}

	@Override
	public synchronized Object getItem(int id) {
		// TODO Auto-generated method stub
		for (Hotel i : listaHotel) {
			if (i.getId() == id)
				return i;
		}
		return null;
	}

	@Override
	public synchronized void removeItem(int id, int row) {
		// TODO Auto-generated method stub
		int index = 0;
		for (Hotel i : listaHotel) {
			if (i.getId() == id) {
				listaHotel.remove(index);
				Agenzia.saveToFile(fileHotel, listaHotel);
				break;
			}
			index++;
		}
		removeRowRange(row, row);
		fireUpdateEvent(new ModelEvent(this));

	}

	@Override
	public synchronized void initFromFile() {
		// TODO Auto-generated method stub
		if (!fileHotel.exists()) {

			try {
				fileHotel.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!fileIdHotel.exists()) {

			try {
				fileIdHotel.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			hotelIn = new FileInputStream(fileHotel);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			ObjectInputStream objInputStream = new ObjectInputStream(hotelIn);
			listaHotel = (ArrayList<Hotel>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file hotel vuoto");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			idHotelIn = new FileInputStream(fileIdHotel);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			ObjectInputStream objInputStream = new ObjectInputStream(idHotelIn);
			idGlobaleHotel = (int) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file id hotel vuoto");
			idGlobaleHotel = 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collections.sort(listaHotel);
		int index = 0;
		for (Hotel i : listaHotel) {
			i.setId(index);
			index++;
		}
		this.idGlobaleHotel = index;
		Agenzia.saveToFile(fileHotel, listaHotel);
		Agenzia.saveToFile(fileIdHotel, this.idGlobaleHotel);

	}

	@Override
	public synchronized void initModel() {
		// TODO Auto-generated method stub
		if (this.getRowCount() > 0) {

			this.removeRowRange(0, this.getRowCount() - 1);

		}
		for (Hotel i : listaHotel) {
			this.addRow(i);
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
		System.out.println("update hotel");
		for (int i = 0; i < listeners.length; i = i + 2) {
			if (listeners[i] == ModelListener.class) {
				((ModelListener) listeners[i + 1]).updateEventOccurred(evt);
			}
		}
	}

}
