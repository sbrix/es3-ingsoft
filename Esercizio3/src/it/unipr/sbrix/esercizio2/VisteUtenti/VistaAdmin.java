package it.unipr.sbrix.esercizio2.VisteUtenti;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.VisteOperazioni.VistaGestioneUtenti;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaAdmin extends VistaOperatore {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8085052232171478200L;
	private Agenzia agenzia = null;

	/**
	 * Create the frame.
	 */

	@SuppressWarnings("static-access")
	public VistaAdmin(int ut, int id, Agenzia ag) {
		super(ut, id, ag);
		lblShowmode.setText("Amministratore");

		JButton btnGestioneUtenti = new JButton("Gestione Utenti");
		panelOperazioni.add(btnGestioneUtenti);

		agenzia = ag;

		this.revalidate();
		this.repaint();
		GridBagConstraints gbc_btnGestioneUtenti = new GridBagConstraints();
		gbc_btnGestioneUtenti.insets = new Insets(0, 0, 0, 5);
		gbc_btnGestioneUtenti.gridx = 0;
		gbc_btnGestioneUtenti.gridy = 2;
		gbc_btnGestioneUtenti.fill = gbc_btnGestioneUtenti.HORIZONTAL;
		btnGestioneUtenti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// TODO Auto-generated method stub

				if (agenzia != null)
					gestioneUtenti(agenzia);
				else {
					JOptionPane.showMessageDialog(null, new JLabel("Errore"));
					System.exit(ERROR);

				}
			}

		});
		this.panelOperazioni.add(btnGestioneUtenti, gbc_btnGestioneUtenti);
		this.panelOperazioni.revalidate();
		this.panelOperazioni.repaint();
	}

	private void gestioneUtenti(Agenzia agenzia) {
		// TODO Auto-generated method stub
		panelVista.removeAll();
		panelVista.add(new VistaGestioneUtenti(agenzia));// era
															// VistaGestioneUtenti
		this.revalidate();
		this.repaint();

	}

}
