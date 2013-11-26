package it.unipr.sbrix.esercizio2.VisteUtenti;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Utente;
import it.unipr.sbrix.esercizio2.VisteOperazioni.VistaGestionePrenotazioni;
import it.unipr.sbrix.esercizio2.VisteOperazioni.VistaGestioneVendite;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class VistaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1951610647929956881L;
	protected JPanel contentPane;
	protected int userType = Utente.CLIENTE;
	protected int personalID = -1;
	private Agenzia agenzia = null;
	protected JPanel panelVista = new JPanel();
	protected JLabel lblShowmode = new JLabel("showMode");
	protected JLabel lblShowutente = new JLabel("showUtente");
	protected JPanel panelOperazioni = new JPanel();
	private final JButton btnVisualizzaAcquisti = new JButton(
			"Visualizza Acquisti");

	/**
	 * Create the frame.
	 */
	public VistaCliente(int uType, int id, Agenzia ag) {
		// setResizable(false);

		setMinimumSize(new Dimension(1024, 600));
		userType = uType;
		agenzia = ag;
		personalID = id;
		if (ag != null) {
			Utente i = (Utente) ag.modelClienti.getItem(personalID);

			if (i != null)
				lblShowutente.setText(i.nome + " " + i.cognome);

		}
		this.lblShowmode.setText("Utente");

		setTitle("Agenzia");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblModalit = new JLabel("Modalit\u00E0:");
		GridBagConstraints gbc_lblModalit = new GridBagConstraints();
		gbc_lblModalit.insets = new Insets(0, 0, 5, 5);
		gbc_lblModalit.gridx = 0;
		gbc_lblModalit.gridy = 0;
		contentPane.add(lblModalit, gbc_lblModalit);

		GridBagConstraints gbc_lblShowmode = new GridBagConstraints();
		gbc_lblShowmode.anchor = GridBagConstraints.WEST;
		gbc_lblShowmode.insets = new Insets(0, 0, 5, 0);
		gbc_lblShowmode.gridx = 1;
		gbc_lblShowmode.gridy = 0;
		contentPane.add(lblShowmode, gbc_lblShowmode);

		JLabel lblUtente = new JLabel("Utente:");
		GridBagConstraints gbc_lblUtente = new GridBagConstraints();
		gbc_lblUtente.insets = new Insets(0, 0, 5, 5);
		gbc_lblUtente.gridx = 0;
		gbc_lblUtente.gridy = 1;
		contentPane.add(lblUtente, gbc_lblUtente);

		GridBagConstraints gbc_lblShowutente = new GridBagConstraints();
		gbc_lblShowutente.anchor = GridBagConstraints.WEST;
		gbc_lblShowutente.insets = new Insets(0, 0, 5, 0);
		gbc_lblShowutente.gridx = 1;
		gbc_lblShowutente.gridy = 1;
		contentPane.add(lblShowutente, gbc_lblShowutente);

		GridBagConstraints gbc_panelOperazioni = new GridBagConstraints();
		gbc_panelOperazioni.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelOperazioni.anchor = GridBagConstraints.NORTH;
		gbc_panelOperazioni.insets = new Insets(0, 0, 0, 5);
		gbc_panelOperazioni.gridx = 0;
		gbc_panelOperazioni.gridy = 2;
		contentPane.add(panelOperazioni, gbc_panelOperazioni);

		JButton btnGestionePrenotazioni = new JButton("Gestione Prenotazioni");
		btnGestionePrenotazioni.setPreferredSize(new Dimension(200, 100));
		btnGestionePrenotazioni.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (agenzia != null)
					gestionePrenotazione(userType, personalID, agenzia);
				else {
					JOptionPane.showMessageDialog(null, new JLabel("Errore"));
					System.exit(ERROR);

				}
			}
		});
		panelOperazioni.setLayout(new GridLayout(10, 1, 0, 0));
		panelOperazioni.add(btnGestionePrenotazioni);
		btnVisualizzaAcquisti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (agenzia != null)
					gestioneVendite(userType, personalID, agenzia);
				else {
					JOptionPane.showMessageDialog(null, new JLabel("Errore"));
					System.exit(ERROR);
				}
			}
		});

		panelOperazioni.add(btnVisualizzaAcquisti);

		GridBagConstraints gbc_panelVista = new GridBagConstraints();
		gbc_panelVista.fill = GridBagConstraints.BOTH;
		gbc_panelVista.gridx = 1;
		gbc_panelVista.gridy = 2;
		panelVista.removeAll();
		panelVista.setLayout(new GridLayout(0, 1, 0, 0));
		panelVista.add(new VistaGestionePrenotazioni(uType, id, ag));
		contentPane.add(panelVista, gbc_panelVista);
	}

	private void gestionePrenotazione(int uType, int id, Agenzia ag) {
		panelVista.removeAll();
		panelVista.add(new VistaGestionePrenotazioni(uType, id, ag));
		this.invalidate();
		this.validate();
		this.repaint();

	}

	private void gestioneVendite(int uType, int id, Agenzia ag) {
		panelVista.removeAll();
		panelVista.add(new VistaGestioneVendite(uType, id, ag));
		this.invalidate();
		this.validate();
		this.repaint();

	}

}
