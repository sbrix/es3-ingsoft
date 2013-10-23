package it.unipr.sbrix.esercizio1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
	// creazione liste gestione agenzia
	static ArrayList<Cliente> listaClienti = new ArrayList<Cliente>(0);
	static ArrayList<Hotel> listaHotel = new ArrayList<Hotel>(0);
	static ArrayList<Volo> listaVoli = new ArrayList<Volo>(0);
	static ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>(
			0);
	static ArrayList<Vendite> listaVendite = new ArrayList<Vendite>(0);
	static ArrayList<ViaggiOrganizzati> listaViaggiOrganizzati = new ArrayList<ViaggiOrganizzati>(
			0);
	static ArrayList<Operatore> listaOperatori = new ArrayList<Operatore>(0);

	// gestione input/output su file
	static final String pathRoot = "/esercizio1/data/";
	static final File rootDir = new File(pathRoot);

	static int idGlobaleOperatori = 0;
	static int idGlobalePrenotazioni = 0;
	static int idGlobaleVendite = 0;

	static final File fileClienti = new File(pathRoot + "clienti.dat");
	static final File fileHotel = new File(pathRoot + "hotel.dat");
	static final File fileVoli = new File(pathRoot + "voli.dat");
	static final File filePrenotazioni = new File(pathRoot + "prenotazioni.dat");
	static final File fileVendite = new File(pathRoot + "vendite.dat");
	static final File fileViaggiOrganizzati = new File(pathRoot + "viaggi.dat");
	static final File fileIdOperatori = new File(pathRoot + "idOperatori.dat");
	static final File fileOperatori = new File(pathRoot + "operatori.dat");
	static final File fileIdPrenotazioni = new File(pathRoot
			+ "idPrenotazioni.dat");
	static final File fileIdVendite = new File(pathRoot + "idVendite.dat");

	static FileInputStream clientiIn = null;
	static FileInputStream hotelIn = null;
	static FileInputStream voliIn = null;
	static FileInputStream prenotazioniIn = null;
	static FileInputStream venditeIn = null;
	static FileInputStream viaggiIn = null;
	static FileInputStream idOperatoriIn = null;
	static FileInputStream operatoriIn = null;
	static FileInputStream idPrenotazioniIn = null;
	static FileInputStream idVenditeIn = null;

	static FileOutputStream clientiOut = null;
	static FileOutputStream hotelOut = null;
	static FileOutputStream voliOut = null;
	static FileOutputStream prenotazioniOut = null;
	static FileOutputStream venditeOut = null;
	static FileOutputStream viaggiOut = null;
	static FileOutputStream idOperatoriOut = null;
	static FileOutputStream operatoriOut = null;
	static FileOutputStream idPrenotazioniOut = null;
	static FileOutputStream idVenditeOut = null;

	static ObjectInputStream objInputStream = null;

	// console per lettura input utente
	static Scanner consoleInput = new Scanner(System.in);

	static void controllaScadenzaPrenotazioni() {

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
		saveToFile(filePrenotazioni, listaPrenotazioni);
		if (flag)
			controllaScadenzaPrenotazioni();
	}

	static void gestioneClienti() {
		System.out.println();
		System.out.println("Menu gestione clienti:");
		System.out.println("1)Aggiungi cliente");
		System.out.println("2)Rimuovi cliente");
		System.out.println("3)Mostra lista clienti");
		System.out.println("4)Torna al menu principale");

		String caso = consoleInput.nextLine();

		caso = caso.trim();
		switch (Integer.parseInt(caso)) {
		case 1: {
			System.out.println("Inserire nome cliente:");
			Cliente cliente = new Cliente();
			cliente.nome = consoleInput.nextLine();
			System.out.println("Inserire cognome cliente:");

			cliente.cognome = consoleInput.nextLine();
			listaClienti.add(cliente);
			saveToFile(fileClienti, listaClienti);

			gestioneClienti();
			break;
		}
		case 2: {
			String nomeTemp, cognomeTemp;
			boolean exit = false;
			System.out.println("Inserire nome cliente da rimuovere:");
			nomeTemp = consoleInput.nextLine();
			System.out.println("Inserire cognome cliente da rimuovere:");
			cognomeTemp = consoleInput.nextLine();
			int index = 0;

			// System.out.println(nomeTemp+cognomeTemp);
			for (Cliente i : listaClienti) {
				if (i.nome.equals(nomeTemp) && i.cognome.equals(cognomeTemp)) {
					listaClienti.remove(index);
					System.out.println("Cliente rimosso");
					saveToFile(fileClienti, listaClienti);
					exit = true;
					break;

				}
				if (exit)
					break;
				index++;

			}
			gestioneClienti();
			break;

		}
		case 3: {
			for (Cliente i : listaClienti) {
				System.out.println(i.toString());
				

			}

			gestioneClienti();
			break;
		}
		case 4: {
			gestioneMenu();

			break;
		}
		default: {
			gestioneClienti();

			break;
		}

		}
	}

	static void gestioneHotel() {

		System.out.println();
		System.out.println("Menu gestione hotel:");
		System.out.println("1)Aggiungi hotel");
		System.out.println("2)Rimuovi hotel");
		System.out.println("3)Mostra lista hotel");
		System.out.println("4)Torna al menu principale");

		String caso = consoleInput.nextLine();

		caso = caso.trim();
		switch (Integer.parseInt(caso)) {
		case 1: {
			System.out.println("Inserire nome hotel:");
			Hotel hotel = new Hotel();
			hotel.nome = consoleInput.nextLine();
			System.out.println("Inserire via hotel:");
			hotel.via = consoleInput.nextLine();
			System.out.println("Inserire città hotel:");
			hotel.citta = consoleInput.nextLine();
			System.out.println("Inserire nazione hotel:");
			hotel.nazione = consoleInput.nextLine();
			listaHotel.add(hotel);
			saveToFile(fileHotel, listaHotel);

			gestioneHotel();
			break;
		}
		case 2: {
			String nomeTemp, viaTemp, cittaTemp, nazioneTemp;
			boolean exit = false;
			System.out.println("Inserire nome Hotel:");
			nomeTemp = consoleInput.nextLine();
			System.out.println("Inserire via hotel da rimuovere:");
			viaTemp = consoleInput.nextLine();
			System.out.println("Inserire citta hotel da rimuovere:");
			cittaTemp = consoleInput.nextLine();
			System.out.println("Inserire nazione hotel da rimuovere:");
			nazioneTemp = consoleInput.nextLine();
			int index = 0;

			// System.out.println(nomeTemp+cognomeTemp);
			for (Hotel i : listaHotel) {
				if (i.nome.equals(nomeTemp) && i.via.equals(viaTemp)
						&& i.citta.equals(cittaTemp)
						&& i.nazione.equals(nazioneTemp)) {
					listaHotel.remove(index);
					System.out.println("Hotel rimosso");
					saveToFile(fileHotel, listaHotel);
					exit = true;
					break;

				}
				if (exit)
					break;
				index++;

			}
			gestioneHotel();
			break;

		}
		case 3: {
			for (Hotel i : listaHotel) {
				System.out.println(i.toString());
				

			}

			gestioneHotel();
			break;
		}
		case 4: {
			gestioneMenu();

			break;
		}
		default: {
			gestioneHotel();

			break;
		}

		}
	}

	static void gestioneMenu() {

		controllaScadenzaPrenotazioni();
		// System.out.println(idGlobaleOperatori);
		System.out.println();
		System.out.println("Menu gestione agenzia:");
		System.out.println("1)Gestione clienti");
		System.out.println("2)Gestione voli");
		System.out.println("3)Gestione hotel");
		System.out.println("4)Gestione viaggi organizzati");
		System.out.println("5)Gestione prenotazioni");
		System.out.println("6)Gestione vendite");
		System.out.println("7)Gestione operatori:");
		System.out.println("8)Esci dal programma");
		System.out.println("Selezionare opzione:");

		String caso = consoleInput.nextLine();

		// caso = caso.trim();
		// System.out.println(Integer.parseInt(caso));
		switch (Integer.parseInt(caso)) {
		case 1: {
			gestioneClienti();
			break;
		}
		case 2: {
			gestioneVoli();
			break;
		}
		case 3: {
			gestioneHotel();
			break;
		}
		case 4: {
			gestioneViaggi();
			break;
		}
		case 5: {
			gestionePrenotazioni();
			break;
		}
		case 6: {
			gestioneVendite();
			break;
		}
		case 7: {
			gestioneOperatori();
		}
		case 8: {

			break;
		}
		default: {
			System.out.println("Selezionare opzione valida");
			gestioneMenu();
			break;
		}
		}

	}

	private static void gestioneOperatori() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Menu gestione operatori:");
		System.out.println("1)Aggiungi operatore");
		System.out.println("2)Rimuovi operatore");
		System.out.println("3)Mostra lista operatore");
		System.out.println("4)Torna al menu principale");

		String caso = consoleInput.nextLine();

		caso = caso.trim();
		switch (Integer.parseInt(caso)) {
		case 1: {
			System.out.println("Inserire nome operatore:");
			Operatore operatore = new Operatore();
			operatore.name = consoleInput.nextLine();
			System.out.println("Inserire cognome operatore:");

			operatore.cognome = consoleInput.nextLine();
			operatore.id_personale = idGlobaleOperatori++;
			listaOperatori.add(operatore);
			saveToFile(fileOperatori, listaOperatori);
			saveToFile(fileIdOperatori, idGlobaleOperatori);

			gestioneOperatori();
			break;
		}
		case 2: {
			String nomeTemp, cognomeTemp;
			boolean exit = false;
			System.out.println("Inserire nome operatore da rimuovere:");
			nomeTemp = consoleInput.nextLine();
			System.out.println("Inserire cognome operatore da rimuovere:");
			cognomeTemp = consoleInput.nextLine();
			int index = 0;

			// System.out.println(nomeTemp+cognomeTemp);
			for (Operatore i : listaOperatori) {
				if (i.name.equals(nomeTemp) && i.cognome.equals(cognomeTemp)) {
					listaClienti.remove(index);
					System.out.println("operatore rimosso");
					saveToFile(fileOperatori, listaOperatori);
					exit = true;
					break;

				}
				if (exit)
					break;
				index++;

			}
			gestioneOperatori();
			break;

		}
		case 3: {
			for (Operatore i : listaOperatori) {
				System.out.println("nome:" + i.name);
				System.out.println("cognome:" + i.cognome);
				System.out.println("id=" + i.id_personale);

			}

			gestioneOperatori();
			break;
		}
		case 4: {
			gestioneMenu();

			break;
		}
		default: {
			gestioneOperatori();

			break;
		}

		}

	}

	static void gestionePrenotazioni() {
		System.out.println("Menu gestione prenotazioni:");
		System.out.println();
		System.out.println("1)Inserire prenotazione personalizzata");
		System.out.println("2)Prenotazione viaggio organizzato");
		System.out.println("3)Lista prenotazioni");
		System.out.println("4)Torna al menu principale");
		String caso = consoleInput.nextLine();
		Prenotazione prenotazione = new Prenotazione();
		caso = caso.trim();
		switch (Integer.parseInt(caso)) {
		case 1: {
			System.out
					.println("Scorrere lista hotel e inserire quello desiderato:");
			int index = 0;
			for (Hotel i : listaHotel) {
				System.out.println(i.toString());
				
				System.out.println("hotel desiderato? y/n");
				String var = consoleInput.nextLine();
				index++;
				if (var.equals("y")) {
					break;
				}

			}
			prenotazione.hotel = listaHotel.get(index);
			index = 0;
			System.out
					.println("Scorrere lista volo di andata e inserire quello desiderato:");
			for (Volo i : listaVoli) {
				System.out.println(i.toString());
				

				System.out.println("volo desiderato? y/n");
				String var = consoleInput.nextLine();
				index++;
				if (var.equals("y")) {
					break;
				}

			}
			prenotazione.andata = listaVoli.get(index);
			index = 0;
			System.out
					.println("Scorrere lista volo di ritorno e inserire quello desiderato:");
			for (Volo i : listaVoli) {
				System.out.println(i.toString());
				

				System.out.println("volo desiderato? y/n");
				String var = consoleInput.nextLine();
				index++;
				if (var.equals("y")) {
					break;
				}

			}
			prenotazione.ritorno = listaVoli.get(index);
			System.out.println("Durata pernottamento:");
			prenotazione.giorniPernottamento = Integer.getInteger(consoleInput
					.nextLine());

			// prenotazione.scadenza = new GregorianCalendar();
			prenotazione.scadenza = Calendar.getInstance().getTimeInMillis() + 2592000000L;// data
																							// di
																							// creazione+30
																							// giorni
																							// in
																							// millis
			index = 0;
			for (Cliente i : listaClienti) {
				System.out.println(i.toString());
				

				System.out.println("cliente desiderato? y/n");
				String var = consoleInput.nextLine();
				index++;
				if (var.equals("y")) {
					break;
				}

			}
			prenotazione.id = idGlobalePrenotazioni++;
			prenotazione.cliente = listaClienti.get(index);
			System.out.println("Inserire id operatore:");
			int id = Integer.valueOf(consoleInput.nextLine());
			index = 0;
			for (Operatore i : listaOperatori) {
				if (i.id_personale == id)
					break;
				index++;
			}
			prenotazione.operatore = listaOperatori.get(index);
			prenotazione.id = idGlobalePrenotazioni++;
			listaPrenotazioni.add(prenotazione);
			saveToFile(filePrenotazioni, listaPrenotazioni);
			saveToFile(fileIdPrenotazioni, idGlobalePrenotazioni);
			gestionePrenotazioni();
			break;

		}
		case 2: {

			System.out
					.println("Scorrere lista viaggi per selezionare quello desiderato");
			int index = 0;
			for (ViaggiOrganizzati i : listaViaggiOrganizzati) {
				System.out.println(i.toString());
				
				System.out.println("viaggio desiderato? y/n");
				String var = consoleInput.nextLine();

				if (var.equals("y")) {
					break;
				}
				index++;

			}
			prenotazione.andata = listaViaggiOrganizzati.get(index).andata;
			prenotazione.ritorno = listaViaggiOrganizzati.get(index).ritorno;
			prenotazione.hotel = listaViaggiOrganizzati.get(index).hotel;
			prenotazione.giorniPernottamento = listaViaggiOrganizzati
					.get(index).durataPernottamento;

			// prenotazione.scadenza = new GregorianCalendar();
			Calendar tempCal = Calendar.getInstance();
			prenotazione.scadenza = (tempCal.getTimeInMillis());// data di
																// creazione+30
			// giorni in millis
			//System.out.println("prima:" + prenotazione.scadenza);
			// prenotazione.scadenza.add(Calendar.MONTH, 1);
			prenotazione.scadenza += 2592000000L;
			//System.out.println("dopo:" + prenotazione.scadenza);
			index = 0;
			for (Cliente i : listaClienti) {
				System.out.println(i.toString());
				

				System.out.println("cliente desiderato? y/n");
				String var = consoleInput.nextLine();

				if (var.equals("y")) {
					break;
				}
				index++;

			}
			prenotazione.id = idGlobalePrenotazioni++;
			prenotazione.cliente = listaClienti.get(index);

			System.out.println("Inserire id operatore:");
			int id = Integer.valueOf(consoleInput.nextLine());
			index = 0;
			for (Operatore i : listaOperatori) {
				if (i.id_personale == id)
					break;
				index++;
			}
			prenotazione.operatore = listaOperatori.get(index);
			prenotazione.id = idGlobalePrenotazioni++;
			listaPrenotazioni.add(prenotazione);
			saveToFile(filePrenotazioni, listaPrenotazioni);
			saveToFile(fileIdPrenotazioni, idGlobalePrenotazioni);
			gestionePrenotazioni();
			break;

		}
		case 3: {

			for (Prenotazione i : listaPrenotazioni) {

				System.out.println(i.toString());
				
			}
			gestionePrenotazioni();
			break;

		}

		case 4: {
			gestioneMenu();
			break;
		}
		}

	}

	static void gestioneVendite() {
		System.out.println("Menu gestione vendite:");
		System.out.println("1)Aggiungi vendita");
		System.out.println("2)Rimuovi vendita");
		System.out.println("3)Lista vendite");
		System.out.println("4)Torna al menu");
		String caso = consoleInput.nextLine();
		switch (Integer.valueOf(caso)) {
		case 1: {
			System.out.println("Inserire id prenotazione:");
			int idCheck = Integer.valueOf(consoleInput.nextLine());
			int index = 0;
			for (Prenotazione i : listaPrenotazioni) {
				if (i.id == idCheck)
					break;
				index++;
			}
			Vendite vendita = new Vendite();
			vendita.andata = listaPrenotazioni.get(index).andata;
			vendita.cliente = listaPrenotazioni.get(index).cliente;
			vendita.giorniPernottamento = listaPrenotazioni.get(index).giorniPernottamento;
			vendita.hotel = listaPrenotazioni.get(index).hotel;
			vendita.operatore = listaPrenotazioni.get(index).operatore;
			vendita.ritorno = listaPrenotazioni.get(index).ritorno;
			vendita.id = idGlobaleVendite++;
			listaVendite.add(vendita);
			listaPrenotazioni.remove(index);
			saveToFile(fileVendite, listaVendite);
			saveToFile(filePrenotazioni, listaPrenotazioni);
			saveToFile(fileIdVendite, idGlobaleVendite);
			gestioneVendite();
			break;
		}
		case 2: {
			System.out.println("Inserire id vendita da rimuovere:");
			int temp = Integer.parseInt(consoleInput.nextLine());
			int index = 0;
			for (Vendite i : listaVendite) {
				if (i.id == temp) {
					listaVendite.remove(index);
					break;
				}
			}
			saveToFile(fileVendite, listaVendite);
			gestioneVendite();
			break;
		}
		case 3: {
			for (Vendite i : listaVendite) {
				System.out.println(i.toString());
				
				gestioneVendite();
				break;
			}
		}
		case 4: {
			gestioneMenu();
			break;
		}
		}
	}

	static void gestioneViaggi() {

		System.out.println();
		System.out.println("Menu gestione viaggi organizzati:");
		System.out.println("1)Aggiungi viaggio");
		System.out.println("2)Rimuovi viaggio");
		System.out.println("3)Mostra lista viaggio");
		System.out.println("4)Torna al menu principale");

		String caso = consoleInput.nextLine();

		caso = caso.trim();
		boolean trovato = false;
		switch (Integer.parseInt(caso)) {
		case 1: {

			ViaggiOrganizzati viaggio = new ViaggiOrganizzati();
			System.out
					.println("Scorrere lista hotel e inserire quello desiderato:");
			int index = 0;
			for (Hotel i : listaHotel) {
				System.out.println(i.toString());
				System.out.println("hotel desiderato? y/n");
				String var = consoleInput.nextLine();

				if (var.equals("y")) {
					trovato = true;
					break;
				}
				index++;

			}
			if (trovato)
				viaggio.hotel = listaHotel.get(index);
			else
				gestioneViaggi();
			index = 0;
			trovato = false;
			System.out
					.println("Scorrere lista volo di andata e inserire quello desiderato:");
			for (Volo i : listaVoli) {
				System.out.println(i.toString());
				

				System.out.println("volo desiderato? y/n");
				String var = consoleInput.nextLine();

				if (var.equals("y")) {
					trovato = true;
					break;
				}
				index++;

			}
			if (trovato)
				viaggio.andata = listaVoli.get(index);
			else
				gestioneViaggi();
			index = 0;
			trovato = false;
			System.out
					.println("Scorrere lista volo di ritorno e inserire quello desiderato:");
			for (Volo i : listaVoli) {
				System.out.println(i.toString());
				

				System.out.println("volo desiderato? y/n");
				String var = consoleInput.nextLine();

				if (var.equals("y")) {
					trovato = true;
					break;
				}

			}
			if (trovato)
				viaggio.ritorno = listaVoli.get(index);
			else
				gestioneViaggi();
			System.out.println("Durata pernottamento:");
			viaggio.durataPernottamento = Integer.parseInt(consoleInput
					.nextLine());
			listaViaggiOrganizzati.add(viaggio);
			saveToFile(fileViaggiOrganizzati, listaViaggiOrganizzati);

			gestioneViaggi();
			break;
		}
		case 2: {
			System.out
					.println("Scorrere lista viaggi per rimuovere quello desiderato");
			int index = 0;
			for (ViaggiOrganizzati i : listaViaggiOrganizzati) {
				System.out.println(i.toString());
				
				System.out.println("viaggio desiderato? y/n");
				String var = consoleInput.nextLine();
				index++;
				if (var.equals("y")) {
					break;
				}

			}
			listaViaggiOrganizzati.remove(index);
			saveToFile(fileViaggiOrganizzati, listaViaggiOrganizzati);
			gestioneViaggi();
			break;

		}
		case 3: {
			for (ViaggiOrganizzati i : listaViaggiOrganizzati) {
				System.out.println(i.toString());
				

			}

			gestioneViaggi();
			break;
		}
		case 4: {
			gestioneMenu();

			break;
		}
		default: {
			gestioneViaggi();

			break;
		}

		}
	}

	static void gestioneVoli() {

		System.out.println();
		System.out.println("Menu gestione voli:");
		System.out.println("1)Aggiungi volo");
		System.out.println("2)Rimuovi volo");
		System.out.println("3)Mostra lista volo");
		System.out.println("4)Torna al menu principale");

		String caso = consoleInput.nextLine();

		caso = caso.trim();
		switch (Integer.parseInt(caso)) {
		case 1: {
			System.out.println("Inserire partenza:");
			Volo volo = new Volo();
			volo.partenza = consoleInput.nextLine();
			System.out.println("Inserire destinazione:");

			volo.destinazione = consoleInput.nextLine();
			listaVoli.add(volo);
			saveToFile(fileVoli, listaVoli);

			gestioneVoli();
			break;
		}
		case 2: {
			String nomeTemp, cognomeTemp;
			boolean exit = false;
			System.out.println("Inserire partenza volo da rimuovere:");
			nomeTemp = consoleInput.nextLine();
			System.out.println("Inserire destinazione volo da rimuovere:");
			cognomeTemp = consoleInput.nextLine();
			int index = 0;

			// System.out.println(nomeTemp+cognomeTemp);
			for (Volo i : listaVoli) {
				if (i.partenza.equals(nomeTemp)
						&& i.destinazione.equals(cognomeTemp)) {
					listaVoli.remove(index);
					System.out.println("Volo rimosso");
					saveToFile(fileVoli, listaVoli);
					exit = true;
					break;

				}
				if (exit)
					break;
				index++;

			}
			gestioneVoli();
			break;

		}
		case 3: {
			for (Volo i : listaVoli) {
				System.out.println(i.toString());
				

			}

			gestioneVoli();
			break;
		}
		case 4: {
			gestioneMenu();

			break;
		}
		default: {
			gestioneVoli();

			break;
		}

		}
	}

	@SuppressWarnings("unchecked")
	static void initFiles() throws IOException, ClassNotFoundException {
		rootDir.mkdirs();// creo la dir se non esiste
		if (!fileClienti.exists()) {

			fileClienti.createNewFile();

		}
		if (!fileHotel.exists()) {

			fileHotel.createNewFile();
		}
		if (!fileVoli.exists()) {

			fileVoli.createNewFile();
		}
		if (!filePrenotazioni.exists()) {

			filePrenotazioni.createNewFile();
		}
		if (!fileVendite.exists()) {

			fileVendite.createNewFile();
		}
		if (!fileViaggiOrganizzati.exists()) {

			fileViaggiOrganizzati.createNewFile();
		}

		if (!fileIdOperatori.exists()) {
			fileIdOperatori.createNewFile();
			idGlobaleOperatori = 0;
		}
		if (!fileOperatori.exists()) {

			fileOperatori.createNewFile();
		}

		if (!fileIdPrenotazioni.exists()) {
			idGlobalePrenotazioni = 0;
			fileIdPrenotazioni.createNewFile();

		}
		if (!fileIdVendite.exists()) {
			idGlobaleVendite = 0;
			fileIdVendite.createNewFile();

		}

		clientiIn = new FileInputStream(fileClienti);
		hotelIn = new FileInputStream(fileHotel);
		voliIn = new FileInputStream(fileVoli);
		prenotazioniIn = new FileInputStream(filePrenotazioni);
		venditeIn = new FileInputStream(fileVendite);
		viaggiIn = new FileInputStream(fileViaggiOrganizzati);
		idOperatoriIn = new FileInputStream(fileIdOperatori);
		operatoriIn = new FileInputStream(fileOperatori);
		idPrenotazioniIn = new FileInputStream(fileIdPrenotazioni);
		idVenditeIn = new FileInputStream(fileIdVendite);

		// inizializzo liste da file
		try {
			objInputStream = new ObjectInputStream(clientiIn);
			listaClienti = (ArrayList<Cliente>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file clienti vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(hotelIn);
			listaHotel = (ArrayList<Hotel>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file hotel vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(voliIn);
			listaVoli = (ArrayList<Volo>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file voli vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(prenotazioniIn);
			listaPrenotazioni = (ArrayList<Prenotazione>) objInputStream
					.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file prenotazioni vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(viaggiIn);
			listaViaggiOrganizzati = (ArrayList<ViaggiOrganizzati>) objInputStream
					.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file viaggi organizzati vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(venditeIn);
			listaVendite = (ArrayList<Vendite>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file vendite vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(operatoriIn);
			listaOperatori = (ArrayList<Operatore>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file operatori vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(idOperatoriIn);
			idGlobaleOperatori = (int) objInputStream.readObject();
			//System.out.println(idGlobaleOperatori);
			objInputStream.close();
		} catch (EOFException e) {
			idGlobaleOperatori = 0;
			System.out.println("file id operatore vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(idVenditeIn);
			idGlobaleVendite = (int) objInputStream.readObject();
			//System.out.println(idGlobaleVendite);
			objInputStream.close();
		} catch (EOFException e) {
			idGlobaleVendite = 0;
			System.out.println("file id vendite vuoto");
		}
		try {
			objInputStream = new ObjectInputStream(idPrenotazioniIn);
			idGlobalePrenotazioni = (int) objInputStream.readObject();
			//System.out.println(idGlobalePrenotazioni);
			objInputStream.close();
		} catch (EOFException e) {
			idGlobalePrenotazioni = 0;
			System.out.println("file id prenotazioni vuoto");
		}

	}

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub

		initFiles();

		gestioneMenu();
		consoleInput.close();

	}

	static Boolean saveToFile(File file, Object obj) {
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

}
