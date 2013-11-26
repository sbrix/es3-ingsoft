package it.unipr.sbrix.esercizio2;

import it.unipr.sbrix.esercizio2.Modelli.ModelHotel;
import it.unipr.sbrix.esercizio2.Modelli.ModelPrenotazioni;
import it.unipr.sbrix.esercizio2.Modelli.ModelUtenti;
import it.unipr.sbrix.esercizio2.Modelli.ModelVendite;
import it.unipr.sbrix.esercizio2.Modelli.ModelViaggiOrganizzati;
import it.unipr.sbrix.esercizio2.Modelli.ModelVoli;
import it.unipr.sbrix.esercizio2.Modelli.ModelEvent;
import it.unipr.sbrix.esercizio2.Modelli.ModelListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.jasypt.util.password.*;

/**
 * @author Luca Sbrissa Matricola 182736 ,Moreno Varoli Matricola ??????
 * 
 */
public class Agenzia {
	// creazione liste gestione agenzia

	public static Boolean saveToFile(File file, Object obj) {
		try {
			FileOutputStream outFile = new FileOutputStream(file);
			ObjectOutputStream objOutputStream = new ObjectOutputStream(outFile);
			objOutputStream.writeObject(obj);
			objOutputStream.flush();
			objOutputStream.close();
			return true;
		} catch (IOException e) {
			System.out.println("Errore scrittura file");
			return false;
		}

	}

	public ModelUtenti modelUtenti = null;
	public ModelUtenti modelClienti = null;
	public ModelVoli modelVoli = null;
	public ModelHotel modelHotel = null;
	public ModelViaggiOrganizzati modelViaggi = null;
	public ModelPrenotazioni modelPrenotazioni = null;
	public ModelVendite modelVendite = null;

	public final static String pathRoot = new String(Agenzia.class
			.getProtectionDomain().getCodeSource().getLocation().getPath())
			+ File.separator + "data" + File.separator;

	private final File rootDir = new File(pathRoot);

	public static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

	public Agenzia() throws ClassNotFoundException, IOException {
		rootDir.mkdirs();// creo la dir se non esiste

		modelUtenti = new ModelUtenti(ModelUtenti.INIT_UTENTE);
		modelClienti = new ModelUtenti(ModelUtenti.INIT_CLIENTE);
		modelVoli = new ModelVoli();
		modelHotel = new ModelHotel();
		modelViaggi = new ModelViaggiOrganizzati();
		modelPrenotazioni = new ModelPrenotazioni();
		modelVendite = new ModelVendite();
		modelUtenti.addUpdateEventListener(new ModelListener() {

			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub

				modelClienti.initFromFile();
				modelClienti.initModel();

			}
		});

		modelClienti.addUpdateEventListener(new ModelListener() {

			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub

				modelUtenti.initFromFile();
				modelUtenti.initModel();

			}
		});

		modelVoli.addUpdateEventListener(new ModelListener() {

			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub

				modelVoli.initFromFile();
				modelVoli.initModel();

			}
		});

		modelHotel.addUpdateEventListener(new ModelListener() {

			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub
				modelHotel.initFromFile();
				modelHotel.initModel();

			}
		});

		modelViaggi.addUpdateEventListener(new ModelListener() {

			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub
				modelViaggi.initFromFile();
				modelViaggi.initModel();

			}
		});

		modelPrenotazioni.addUpdateEventListener(new ModelListener() {

			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub
				modelPrenotazioni.initFromFile();
				modelPrenotazioni.initModel();

			}
		});
		modelVendite.addUpdateEventListener(new ModelListener() {

			@Override
			public void updateEventOccurred(ModelEvent evt) {
				// TODO Auto-generated method stub
				modelVendite.initFromFile();
				modelVendite.initModel();

			}
		});
	}

}