package it.unipr.sbrix.esercizio2;

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
import org.jasypt.util.password.*;

/**
 * @author Luca Sbrissa Matricola 182736 ,Moreno Varoli Matricola ??????
 * 
 */
public class Agenzia {
	// creazione liste gestione agenzia
	public ArrayList<Utente> listaUtenti = new ArrayList<Utente>(0);
	public ArrayList<Hotel> listaHotel = new ArrayList<Hotel>(0);
	public ArrayList<Volo> listaVoli = new ArrayList<Volo>(0);
	public ArrayList<Prenotazione> listaPrenotazioni = new ArrayList<Prenotazione>(
			0);
	public ArrayList<Vendita> listaVendite = new ArrayList<Vendita>(0);
	public ArrayList<ViaggioOrganizzato> listaViaggiOrganizzati = new ArrayList<ViaggioOrganizzato>(
			0);
	// ArrayList<Operatore> listaOperatori = new ArrayList<Operatore>(0);

	// gestione input/output su file
	/*private final String pathRoot = File.separator + "esercizio1"
			+ File.separator + "data" + File.separator;*/
	private final String pathRoot = Agenzia.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private final File rootDir = new File(pathRoot);

	public int idGlobaleUtenti = 0;
	public int idGlobalePrenotazioni = 0;
	public int idGlobaleVendite = 0;

	public BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

	public final File fileUtenti = new File(pathRoot + "utenti.dat");
	public final File fileHotel = new File(pathRoot + "hotel.dat");
	public final File fileVoli = new File(pathRoot + "voli.dat");
	public final File filePrenotazioni = new File(pathRoot + "prenotazioni.dat");
	public final File fileVendite = new File(pathRoot + "vendite.dat");
	public final File fileViaggiOrganizzati = new File(pathRoot + "viaggi.dat");
	public final File fileIdUtenti = new File(pathRoot + "idUtenti.dat");
	// final File fileOperatori = new File(pathRoot + "operatori.dat");
	public final File fileIdPrenotazioni = new File(pathRoot
			+ "idPrenotazioni.dat");
	public final File fileIdVendite = new File(pathRoot + "idVendite.dat");

	public FileInputStream utentiIn = null;
	public FileInputStream hotelIn = null;
	public FileInputStream voliIn = null;
	public FileInputStream prenotazioniIn = null;
	public FileInputStream venditeIn = null;
	public FileInputStream viaggiIn = null;
	public FileInputStream idUtentiIn = null;
	// FileInputStream operatoriIn = null;
	public FileInputStream idPrenotazioniIn = null;
	public FileInputStream idVenditeIn = null;

	public FileOutputStream utentiOut = null;
	public FileOutputStream hotelOut = null;
	public FileOutputStream voliOut = null;
	public FileOutputStream prenotazioniOut = null;
	public FileOutputStream venditeOut = null;
	public FileOutputStream viaggiOut = null;
	public FileOutputStream idUtentiOut = null;
	// FileOutputStream operatoriOut = null;
	public FileOutputStream idPrenotazioniOut = null;
	public FileOutputStream idVenditeOut = null;

	public ObjectInputStream objInputStream = null;

	// console per lettura input utente
	public Scanner consoleInput = new Scanner(System.in);

	public Agenzia() throws ClassNotFoundException, IOException {
		initFiles();
		// TODO Auto-generated constructor stub
	}

	private void controllaScadenzaPrenotazioni() {

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

	private void gestioneClienti() {
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
			Utente cliente = new Utente();
			cliente.nome = consoleInput.nextLine();
			System.out.println("Inserire cognome cliente:");

			cliente.cognome = consoleInput.nextLine();
			listaUtenti.add(cliente);
			saveToFile(fileUtenti, listaUtenti);

			gestioneClienti();
			break;
		}
		case 2: {
			/*
			 * String nomeTemp, cognomeTemp; boolean exit = false;
			 * System.out.println("Inserire nome cliente da rimuovere:");
			 * nomeTemp = consoleInput.nextLine();
			 * System.out.println("Inserire cognome cliente da rimuovere:");
			 * cognomeTemp = consoleInput.nextLine(); int index = 0;
			 * 
			 * // System.out.println(nomeTemp+cognomeTemp); for (Cliente i :
			 * listaClienti) { if (i.nome.equals(nomeTemp) &&
			 * i.cognome.equals(cognomeTemp)) { listaClienti.remove(index);
			 * System.out.println("Cliente rimosso"); exit = true; break;
			 * 
			 * } if (exit) break; index++;
			 * 
			 * }
			 */
			removeItemFromList(listaUtenti);
			saveToFile(fileUtenti, listaUtenti);
			gestioneClienti();
			break;

		}
		case 3: {
			/*
			 * if (!listaClienti.isEmpty()) { for (Cliente i : listaClienti) {
			 * System.out.println(i.toString());
			 * 
			 * } } else System.out.println("Lista Clienti vuota");
			 */
			printList(listaUtenti, new Utente());

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

	private void gestioneHotel() {

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
			/*
			 * String nomeTemp, viaTemp, cittaTemp, nazioneTemp; boolean exit =
			 * false; System.out.println("Inserire nome Hotel:"); nomeTemp =
			 * consoleInput.nextLine();
			 * System.out.println("Inserire via hotel da rimuovere:"); viaTemp =
			 * consoleInput.nextLine();
			 * System.out.println("Inserire citta hotel da rimuovere:");
			 * cittaTemp = consoleInput.nextLine();
			 * System.out.println("Inserire nazione hotel da rimuovere:");
			 * nazioneTemp = consoleInput.nextLine(); int index = 0;
			 * 
			 * // System.out.println(nomeTemp+cognomeTemp); for (Hotel i :
			 * listaHotel) { if (i.nome.equals(nomeTemp) &&
			 * i.via.equals(viaTemp) && i.citta.equals(cittaTemp) &&
			 * i.nazione.equals(nazioneTemp)) { listaHotel.remove(index);
			 * System.out.println("Hotel rimosso"); exit = true; break;
			 * 
			 * } if (exit) break; index++;
			 * 
			 * }
			 */
			removeItemFromList(listaHotel);
			saveToFile(fileHotel, listaHotel);
			gestioneHotel();
			break;

		}
		case 3: {
			/*
			 * if (!listaHotel.isEmpty()) { for (Hotel i : listaHotel) {
			 * System.out.println(i.toString());
			 * 
			 * } } else System.out.println("Lista hotel vuota");
			 */

			printList(listaHotel, new Hotel());
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

	void gestioneMenu() {

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
		// printList(listaClienti, new Cliente());
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

	private void gestioneOperatori() {
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
			// Operatore operatore = new Operatore();
			// operatore.name = consoleInput.nextLine();
			System.out.println("Inserire cognome operatore:");

			// operatore.cognome = consoleInput.nextLine();
			// operatore.id_personale = idGlobaleUtenti++;
			// listaOperatori.add(operatore);
			// saveToFile(fileOperatori, listaOperatori);
			// saveToFile(fileIdOperatori, idGlobaleUtenti);

			gestioneOperatori();
			break;
		}
		case 2: {
			/*
			 * String nomeTemp, cognomeTemp; boolean exit = false;
			 * System.out.println("Inserire nome operatore da rimuovere:");
			 * nomeTemp = consoleInput.nextLine();
			 * System.out.println("Inserire cognome operatore da rimuovere:");
			 * cognomeTemp = consoleInput.nextLine(); int index = 0;
			 * 
			 * // System.out.println(nomeTemp+cognomeTemp); for (Operatore i :
			 * listaOperatori) { if (i.name.equals(nomeTemp) &&
			 * i.cognome.equals(cognomeTemp)) { listaOperatori.remove(index);
			 * System.out.println("operatore rimosso"); exit = true; break;
			 * 
			 * } if (exit) break; index++;
			 * 
			 * }
			 */
			// removeItemFromList(listaOperatori);
			// saveToFile(fileOperatori, listaOperatori);
			gestioneOperatori();
			break;

		}
		case 3: {
			/*
			 * for (Operatore i : listaOperatori) {
			 * System.out.println(i.toString());
			 * 
			 * }
			 */
			// printList(listaOperatori, new Operatore());
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

	private void gestionePrenotazioni() {
		System.out.println("Menu gestione prenotazioni:");
		System.out.println();
		System.out.println("1)Inserire prenotazione personalizzata");
		System.out.println("2)Prenotazione viaggio organizzato");
		System.out.println("3)Lista prenotazioni");
		System.out.println("4)Torna al menu principale");
		String caso = consoleInput.nextLine();

		caso = caso.trim();
		switch (Integer.parseInt(caso)) {
		case 1: {
			// int index = 0;
			/*
			 * System.out
			 * .println("Scorrere lista hotel e inserire quello desiderato:");
			 * for (Hotel i : listaHotel) { System.out.println(i.toString());
			 * 
			 * System.out.println("hotel desiderato? y/n"); String var =
			 * consoleInput.nextLine(); index++; if (var.equals("y")) { break; }
			 * 
			 * } if (!listaHotel.isEmpty()) prenotazione.hotel =
			 * listaHotel.get(index); }
			 */
			Prenotazione prenotazione = new Prenotazione();
			if (!listaHotel.isEmpty()) {
				System.out.println("Selezionare hotel:");
				while (prenotazione.hotel == null)
					prenotazione.hotel = getItemFromList(listaHotel);
			} else {
				System.out.println("Lista vuota impossibile procedere");
				gestionePrenotazioni();
			}

			/*
			 * index = 0; System.out .println(
			 * "Scorrere lista volo di andata e inserire quello desiderato:");
			 * for (Volo i : listaVoli) { System.out.println(i.toString());
			 * 
			 * System.out.println("volo desiderato? y/n"); String var =
			 * consoleInput.nextLine(); index++; if (var.equals("y")) { break; }
			 * 
			 * } if (!listaVoli.isEmpty()) prenotazione.andata =
			 * listaVoli.get(index);
			 */
			if (!listaVoli.isEmpty()) {
				System.out.println("Selezionare volo di andata:");
				while (prenotazione.andata == null)
					prenotazione.andata = getItemFromList(listaVoli);
				System.out.println("Selezionare volo di ritorno");
				while (prenotazione.ritorno == null)
					prenotazione.ritorno = getItemFromList(listaVoli);
			} else {
				System.out.println("Lista vuota impossibile procedere");
				gestionePrenotazioni();
			}/*
			 * index = 0; System.out .println(
			 * "Scorrere lista volo di ritorno e inserire quello desiderato:");
			 * for (Volo i : listaVoli) { System.out.println(i.toString());
			 * 
			 * System.out.println("volo desiderato? y/n"); String var =
			 * consoleInput.nextLine(); index++; if (var.equals("y")) { break; }
			 * 
			 * } if (!listaVoli.isEmpty()) prenotazione.ritorno =
			 * listaVoli.get(index);
			 */

			System.out.println("Durata pernottamento:");
			prenotazione.durataPernottamento = Integer.parseInt(consoleInput
					.nextLine());

			// prenotazione.scadenza = new GregorianCalendar();
			prenotazione.scadenza = Calendar.getInstance().getTimeInMillis() + 2592000000L;// data
			// di
			// creazione+30
			// giorni
			// in
			// millis
			/*
			 * index = 0; for (Cliente i : listaClienti) {
			 * System.out.println(i.toString());
			 * 
			 * System.out.println("cliente desiderato? y/n"); String var =
			 * consoleInput.nextLine(); index++; if (var.equals("y")) { break; }
			 * 
			 * } prenotazione.id = idGlobalePrenotazioni++; if
			 * (!listaClienti.isEmpty()) prenotazione.cliente =
			 * listaClienti.get(index);
			 */

			if (!listaUtenti.isEmpty()) {
				System.out.println("Selezionare cliente:");
				while (prenotazione.cliente == null)
					prenotazione.cliente = getItemFromList(listaUtenti);
			} else {
				System.out.println("Lista vuota impossibile procedere");
				gestionePrenotazioni();
			}
			/*
			 * System.out.println("Inserire id operatore:"); int id =
			 * Integer.valueOf(consoleInput.nextLine()); index = 0; for
			 * (Operatore i : listaOperatori) { if (i.id_personale == id) break;
			 * index++; } if (!listaOperatori.isEmpty()) prenotazione.operatore
			 * = listaOperatori.get(index);
			 */
			/*
			 * if (!listaOperatori.isEmpty()) {
			 * System.out.println("Selezionare operatore:"); while
			 * (prenotazione.operatore == null) prenotazione.operatore =
			 * getItemFromList(listaOperatori); } else {
			 * System.out.println("Lista vuota impossibile procedere");
			 * gestionePrenotazioni(); }
			 */

			prenotazione.id = idGlobalePrenotazioni++;
			listaPrenotazioni.add(prenotazione);
			saveToFile(filePrenotazioni, listaPrenotazioni);
			saveToFile(fileIdPrenotazioni, idGlobalePrenotazioni);
			gestionePrenotazioni();
			break;

		}
		case 2: {

			/*
			 * System.out
			 * .println("Scorrere lista viaggi per selezionare quello desiderato"
			 * ); int index = 0; for (ViaggiOrganizzati i :
			 * listaViaggiOrganizzati) { System.out.println(i.toString());
			 * 
			 * System.out.println("viaggio desiderato? y/n"); String var =
			 * consoleInput.nextLine();
			 * 
			 * if (var.equals("y")) { break; } index++;
			 * 
			 * } prenotazione.andata = listaViaggiOrganizzati.get(index).andata;
			 * prenotazione.ritorno = listaViaggiOrganizzati.get(index).ritorno;
			 * prenotazione.hotel = listaViaggiOrganizzati.get(index).hotel;
			 * prenotazione.durataPernottamento = listaViaggiOrganizzati
			 * .get(index).durataPernottamento;
			 */

			// prenotazione.scadenza = new GregorianCalendar();
			// Calendar tempCal = Calendar.getInstance();
			// prenotazione.scadenza = (tempCal.getTimeInMillis());// data di
			// creazione+30
			// giorni in millis
			// System.out.println("prima:" + prenotazione.scadenza);
			// prenotazione.scadenza.add(Calendar.MONTH, 1);
			Prenotazione prenotazione = new Prenotazione();
			if (!listaViaggiOrganizzati.isEmpty()) {
				System.out.println("Selezionare Viaggio Organizzato:");
				while (prenotazione.hotel == null) {
					ViaggioOrganizzato temp = getItemFromList(listaViaggiOrganizzati);
					prenotazione.andata = temp.andata;
					prenotazione.ritorno = temp.ritorno;
					prenotazione.hotel = temp.hotel;
					prenotazione.durataPernottamento = temp.durataPernottamento;

				}
			} else {
				System.out.println("Lista vuota impossibile procedere");
				gestionePrenotazioni();
			}
			prenotazione.scadenza = Calendar.getInstance().getTimeInMillis() + 2592000000L;
			;
			// System.out.println("dopo:" + prenotazione.scadenza);
			/*
			 * index = 0; for (Cliente i : listaClienti) {
			 * System.out.println(i.toString());
			 * 
			 * System.out.println("cliente desiderato? y/n"); String var =
			 * consoleInput.nextLine();
			 * 
			 * if (var.equals("y")) { break; } index++;
			 * 
			 * }
			 */
			if (!listaUtenti.isEmpty()) {
				System.out.println("Selezionare cliente:");
				while (prenotazione.cliente == null)
					prenotazione.cliente = getItemFromList(listaUtenti);
			} else {
				System.out.println("Lista vuota impossibile procedere");
				gestionePrenotazioni();
			}
			prenotazione.id = idGlobalePrenotazioni++;
			/*
			 * if (!listaClienti.isEmpty()) prenotazione.cliente =
			 * listaClienti.get(index);
			 */

			/*
			 * System.out.println("Inserire id operatore:"); int id =
			 * Integer.valueOf(consoleInput.nextLine()); index = 0; for
			 * (Operatore i : listaOperatori) { if (i.id_personale == id) break;
			 * index++; } if (!listaOperatori.isEmpty()) prenotazione.operatore
			 * = listaOperatori.get(index); prenotazione.id =
			 * idGlobalePrenotazioni++;
			 */
			/*
			 * if (!listaOperatori.isEmpty()) {
			 * System.out.println("Selezionare operatore:"); while
			 * (prenotazione.operatore == null) prenotazione.operatore =
			 * getItemFromList(listaOperatori); } else {
			 * System.out.println("Lista vuota impossibile procedere");
			 * gestionePrenotazioni(); }
			 */
			listaPrenotazioni.add(prenotazione);
			saveToFile(filePrenotazioni, listaPrenotazioni);
			saveToFile(fileIdPrenotazioni, idGlobalePrenotazioni);
			gestionePrenotazioni();
			break;

		}
		case 3: {

			/*
			 * for (Prenotazione i : listaPrenotazioni) {
			 * 
			 * System.out.println(i.toString());
			 * 
			 * }
			 */
			printList(listaPrenotazioni, new Prenotazione());
			gestionePrenotazioni();
			break;

		}

		case 4: {
			gestioneMenu();
			break;
		}
		}

	}

	private void gestioneVendite() {
		System.out.println("Menu gestione vendite:");
		System.out.println("1)Aggiungi vendita da prenotazione");
		System.out.println("2)Rimuovi vendita");
		System.out.println("3)Lista vendite");
		System.out.println("4)Torna al menu");
		String caso = consoleInput.nextLine();
		switch (Integer.valueOf(caso)) {
		case 1: {
			if (!listaPrenotazioni.isEmpty()) {
				System.out.println("Inserire id prenotazione:");
				int idCheck = Integer.parseInt(consoleInput.nextLine());
				int index = 0;
				try {
					for (Prenotazione i : listaPrenotazioni) {
						if (i.id == idCheck)
							break;
						index++;
					}
					Vendita vendita = new Vendita();

					{
						vendita.andata = listaPrenotazioni.get(index).andata;
						vendita.cliente = listaPrenotazioni.get(index).cliente;
						vendita.durataPernottamento = listaPrenotazioni
								.get(index).durataPernottamento;
						vendita.hotel = listaPrenotazioni.get(index).hotel;
						// vendita.operatore =
						// listaPrenotazioni.get(index).operatore;
						vendita.ritorno = listaPrenotazioni.get(index).ritorno;

						vendita.id = idGlobaleVendite++;
						listaVendite.add(vendita);
						listaPrenotazioni.remove(index);
					}

					// gestioneVendite();
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Prenotazione non valida");
				}
				saveToFile(fileVendite, listaVendite);
				saveToFile(filePrenotazioni, listaPrenotazioni);
				saveToFile(fileIdVendite, idGlobaleVendite);
				gestioneVendite();
				break;

			}

			else {
				System.out.println("Nessuna prenotazione presente");
				gestioneVendite();
				break;
			}
		}
		case 2: {
			/*
			 * System.out.println("Inserire id vendita da rimuovere:"); int temp
			 * = Integer.parseInt(consoleInput.nextLine()); int index = 0; for
			 * (Vendite i : listaVendite) { if (i.id == temp) {
			 * listaVendite.remove(index); break; } }
			 */
			removeItemFromList(listaVendite);
			saveToFile(fileVendite, listaVendite);
			gestioneVendite();
			break;
		}
		case 3: {
			/*
			 * for (Vendite i : listaVendite) {
			 * System.out.println(i.toString());
			 * 
			 * break; }
			 */
			printList(listaVendite, new Vendita());
			gestioneVendite();
			break;
		}
		case 4: {
			gestioneMenu();
			break;
		}
		}
	}

	private void gestioneViaggi() {

		System.out.println();
		System.out.println("Menu gestione viaggi organizzati:");
		System.out.println("1)Aggiungi viaggio");
		System.out.println("2)Rimuovi viaggio");
		System.out.println("3)Mostra lista viaggio");
		System.out.println("4)Torna al menu principale");

		String caso = consoleInput.nextLine();

		caso = caso.trim();
		// boolean trovato = false;
		switch (Integer.parseInt(caso)) {
		case 1: {

			ViaggioOrganizzato viaggio = new ViaggioOrganizzato();
			/*
			 * System.out
			 * .println("Scorrere lista hotel e inserire quello desiderato:");
			 * int index = 0; for (Hotel i : listaHotel) {
			 * System.out.println(i.toString());
			 * System.out.println("hotel desiderato? y/n"); String var =
			 * consoleInput.nextLine();
			 * 
			 * if (var.equals("y")) { trovato = true; break; } index++;
			 * 
			 * } if (trovato) viaggio.hotel = listaHotel.get(index); else
			 * gestioneViaggi();
			 */
			if (!listaHotel.isEmpty()) {
				System.out.println("Selezionare hotel:");
				while (viaggio.hotel == null)
					viaggio.hotel = getItemFromList(listaHotel);
			} else {
				System.out.println("Lista vuota impossibile procedere");
				gestioneViaggi();
			}
			/*
			 * index = 0; trovato = false; System.out .println(
			 * "Scorrere lista volo di andata e inserire quello desiderato:");
			 * for (Volo i : listaVoli) { System.out.println(i.toString());
			 * 
			 * System.out.println("volo desiderato? y/n"); String var =
			 * consoleInput.nextLine();
			 * 
			 * if (var.equals("y")) { trovato = true; break; } index++;
			 * 
			 * } if (trovato) viaggio.andata = listaVoli.get(index); else
			 * gestioneViaggi(); index = 0; trovato = false; System.out
			 * .println(
			 * "Scorrere lista volo di ritorno e inserire quello desiderato:");
			 * for (Volo i : listaVoli) { System.out.println(i.toString());
			 * 
			 * System.out.println("volo desiderato? y/n"); String var =
			 * consoleInput.nextLine();
			 * 
			 * if (var.equals("y")) { trovato = true; break; }
			 * 
			 * } if (trovato) viaggio.ritorno = listaVoli.get(index); else
			 * gestioneViaggi();
			 */
			if (!listaVoli.isEmpty()) {
				System.out.println("Selezionare volo andata:");
				while (viaggio.andata == null)
					viaggio.andata = getItemFromList(listaVoli);
				while (viaggio.ritorno == null)
					viaggio.ritorno = getItemFromList(listaVoli);
			} else {
				System.out.println("Lista vuota impossibile procedere");
				gestioneViaggi();
			}
			System.out.println("Durata pernottamento:");
			viaggio.durataPernottamento = Integer.parseInt(consoleInput
					.nextLine());
			listaViaggiOrganizzati.add(viaggio);
			saveToFile(fileViaggiOrganizzati, listaViaggiOrganizzati);

			gestioneViaggi();
			break;
		}
		case 2: {
			/*
			 * System.out
			 * .println("Scorrere lista viaggi per rimuovere quello desiderato"
			 * ); int index = 0; for (ViaggiOrganizzati i :
			 * listaViaggiOrganizzati) { System.out.println(i.toString());
			 * 
			 * System.out.println("viaggio desiderato? y/n"); String var =
			 * consoleInput.nextLine(); index++; if (var.equals("y")) { break; }
			 * 
			 * } listaViaggiOrganizzati.remove(index);
			 */
			removeItemFromList(listaViaggiOrganizzati);
			saveToFile(fileViaggiOrganizzati, listaViaggiOrganizzati);
			gestioneViaggi();
			break;

		}
		case 3: {
			/*
			 * for (ViaggiOrganizzati i : listaViaggiOrganizzati) {
			 * System.out.println(i.toString());
			 * 
			 * }
			 */

			printList(listaViaggiOrganizzati, new ViaggioOrganizzato());
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

	private void gestioneVoli() {

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
			/*
			 * String nomeTemp, cognomeTemp; boolean exit = false;
			 * System.out.println("Inserire partenza volo da rimuovere:");
			 * nomeTemp = consoleInput.nextLine();
			 * System.out.println("Inserire destinazione volo da rimuovere:");
			 * cognomeTemp = consoleInput.nextLine(); int index = 0;
			 * 
			 * // System.out.println(nomeTemp+cognomeTemp); for (Volo i :
			 * listaVoli) { if (i.partenza.equals(nomeTemp) &&
			 * i.destinazione.equals(cognomeTemp)) { listaVoli.remove(index);
			 * System.out.println("Volo rimosso"); saveToFile(fileVoli,
			 * listaVoli); exit = true; break;
			 * 
			 * } if (exit) break; index++;
			 * 
			 * }
			 */
			removeItemFromList(listaVoli);
			saveToFile(fileVoli, listaVoli);
			gestioneVoli();
			break;

		}
		case 3: {
			/*
			 * for (Volo i : listaVoli) { System.out.println(i.toString());
			 * 
			 * }
			 */

			printList(listaVoli, new Volo());
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
	void initFiles() throws IOException, ClassNotFoundException {
		rootDir.mkdirs();// creo la dir se non esiste
		if (!fileUtenti.exists()) {

			fileUtenti.createNewFile();

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

		if (!fileIdUtenti.exists()) {
			fileIdUtenti.createNewFile();
			idGlobaleUtenti = 0;
		}
		/*
		 * if (!fileOperatori.exists()) {
		 * 
		 * fileOperatori.createNewFile(); }
		 */

		if (!fileIdPrenotazioni.exists()) {
			idGlobalePrenotazioni = 0;
			fileIdPrenotazioni.createNewFile();

		}
		if (!fileIdVendite.exists()) {
			idGlobaleVendite = 0;
			fileIdVendite.createNewFile();

		}

		utentiIn = new FileInputStream(fileUtenti);
		hotelIn = new FileInputStream(fileHotel);
		voliIn = new FileInputStream(fileVoli);
		prenotazioniIn = new FileInputStream(filePrenotazioni);
		venditeIn = new FileInputStream(fileVendite);
		viaggiIn = new FileInputStream(fileViaggiOrganizzati);
		idUtentiIn = new FileInputStream(fileIdUtenti);
		// operatoriIn = new FileInputStream(fileOperatori);
		idPrenotazioniIn = new FileInputStream(fileIdPrenotazioni);
		idVenditeIn = new FileInputStream(fileIdVendite);

		// inizializzo liste da file
		try {
			objInputStream = new ObjectInputStream(utentiIn);
			listaUtenti = (ArrayList<Utente>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			// System.out.println("file clienti vuoto");
			// se lista vuota devo creare l accound admin di default
			Utente admin = new Utente("admin", "admin", "admin", "admin", this);
			admin.setUserType(Utente.ADMIN);
			listaUtenti.add(admin);
			this.saveToFile(fileUtenti, this.listaUtenti);
			System.out.println("Utente admin creato");

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
			listaViaggiOrganizzati = (ArrayList<ViaggioOrganizzato>) objInputStream
					.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file viaggi organizzati vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(venditeIn);
			listaVendite = (ArrayList<Vendita>) objInputStream.readObject();
			objInputStream.close();
		} catch (EOFException e) {
			System.out.println("file vendite vuoto");
		}

		/*
		 * try { objInputStream = new ObjectInputStream(operatoriIn);
		 * listaOperatori = (ArrayList<Operatore>) objInputStream.readObject();
		 * objInputStream.close(); } catch (EOFException e) {
		 * System.out.println("file operatori vuoto"); }
		 */

		try {
			objInputStream = new ObjectInputStream(idUtentiIn);
			idGlobaleUtenti = (int) objInputStream.readObject();
			// System.out.println(idGlobaleOperatori);
			objInputStream.close();
		} catch (EOFException e) {
			idGlobaleUtenti = 0;
			this.saveToFile(fileIdUtenti, this.idGlobaleUtenti);
			// System.out.println("file id operatore vuoto");
		}

		try {
			objInputStream = new ObjectInputStream(idVenditeIn);
			idGlobaleVendite = (int) objInputStream.readObject();
			// System.out.println(idGlobaleVendite);
			objInputStream.close();
		} catch (EOFException e) {
			idGlobaleVendite = 0;
			System.out.println("file id vendite vuoto");
		}
		try {
			objInputStream = new ObjectInputStream(idPrenotazioniIn);
			idGlobalePrenotazioni = (int) objInputStream.readObject();
			// System.out.println(idGlobalePrenotazioni);
			objInputStream.close();
		} catch (EOFException e) {
			idGlobalePrenotazioni = 0;
			System.out.println("file id prenotazioni vuoto");
		}

	}

	/*
	 * public void main(String[] args) throws IOException,
	 * ClassNotFoundException { // TODO Auto-generated method stub
	 * 
	 * initFiles();
	 * 
	 * gestioneMenu(); consoleInput.close();
	 * 
	 * }
	 */
	private <T> void printList(final ArrayList<T> lista, final T tipo) {

		if (!lista.isEmpty()) {

			for (T i : lista) {

				System.out.println(i.toString());
			}

		}

		else {

			System.out.println("Lista " + tipo.getClass().getSimpleName()
					+ " vuota");

		}
	}

	private <T> void removeItemFromList(ArrayList<T> lista) {
		int index = 0;
		Boolean selected = false;
		if (!lista.isEmpty()) {
			for (T i : lista) {
				System.out.println(i.toString());
				System.out.println("Rimuovere questo oggetto? y/n");
				if (consoleInput.nextLine().equals("y")) {
					lista.remove(index);
					selected = true;
				}
				if (selected) {
					System.out.println("Oggetto rimosso");
					break;
				}
				index++;
			}

		} else
			System.out
					.println("Impossibile rimuovere oggetto da una lista vuota");
	}

	private <T> T getItemFromList(ArrayList<T> lista) {
		int index = 0;
		Boolean selected = false;
		if (!lista.isEmpty()) {
			// System.out.println("Selezionare un "
			// + tipo.getClass().getSimpleName() + " dalla lista:");
			for (T i : lista) {
				System.out.println(i.toString());
				System.out.println("Selezionare questo oggetto? y/n");
				if (consoleInput.nextLine().equals("y")) {

					selected = true;
				}
				if (selected)
					return lista.get(index);
				index++;
			}

		} else {
			// System.out.println("Nessun elemento selezionato ");
			return null;
		}
		return null;
	}

	public Boolean saveToFile(File file, Object obj) {
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