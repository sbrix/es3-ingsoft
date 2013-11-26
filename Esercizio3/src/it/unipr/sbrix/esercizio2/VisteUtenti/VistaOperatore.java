package it.unipr.sbrix.esercizio2.VisteUtenti;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.VisteOperazioni.VistaGestioneClienti;
import it.unipr.sbrix.esercizio2.VisteOperazioni.VistaGestioneHotel;
import it.unipr.sbrix.esercizio2.VisteOperazioni.VistaGestioneViaggiOrganizzati;
import it.unipr.sbrix.esercizio2.VisteOperazioni.VistaGestioneVoli;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaOperatore extends VistaCliente {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2492679810842288395L;

	public VistaOperatore(final int userType, final int idOperatore,
			final Agenzia ag) {
		super(userType, idOperatore, ag);

		JButton btnGestioneClienti = new JButton("Gestione Clienti");
		btnGestioneClienti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelVista.removeAll();
				panelVista.add(new VistaGestioneClienti(ag));
				panelVista.revalidate();
				panelVista.repaint();
			}
		});
		panelOperazioni.add(btnGestioneClienti);

		JButton btnGestioneHotel = new JButton("Gestione Hotel");
		btnGestioneHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelVista.removeAll();
				panelVista
						.add(new VistaGestioneHotel(userType, idOperatore, ag));
				panelVista.revalidate();
				panelVista.repaint();
			}
		});
		panelOperazioni.add(btnGestioneHotel);

		JButton btnGestioneVoli = new JButton("Gestione Voli");
		btnGestioneVoli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelVista.removeAll();
				panelVista
						.add(new VistaGestioneVoli(userType, idOperatore, ag));
				panelVista.revalidate();
				panelVista.repaint();
			}
		});
		panelOperazioni.add(btnGestioneVoli);

		JButton btnGestioneViaggiOrganizzati = new JButton(
				"Gestione Viaggi Organizzati");
		btnGestioneViaggiOrganizzati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelVista.removeAll();
				panelVista.add(new VistaGestioneViaggiOrganizzati(userType,
						idOperatore, ag));
				panelVista.revalidate();
				panelVista.repaint();
			}
		});
		panelOperazioni.add(btnGestioneViaggiOrganizzati);
		this.lblShowmode.setText("Operatore");
		this.revalidate();
		this.repaint();

		// TODO Auto-generated constructor stub

	}

}
