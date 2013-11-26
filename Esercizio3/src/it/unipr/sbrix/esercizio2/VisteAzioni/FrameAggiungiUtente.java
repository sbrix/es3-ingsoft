package it.unipr.sbrix.esercizio2.VisteAzioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Utente;
import it.unipr.sbrix.esercizio2.Modelli.ModelUtenti;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.Choice;

public class FrameAggiungiUtente extends FrameAggiungiCliente {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3760686615449317410L;
	private Choice choiceUserType;

	/**
	 * Create the frame.
	 */
	public FrameAggiungiUtente(final Agenzia ag_, final JPanel panel_,
			ModelUtenti model_) {

		super(ag_, panel_, model_);
		this.setTitle("Aggiungi utente");

		JLabel lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 4;
		contentPane.add(lblTipo, gbc_lblTipo);

		choiceUserType = new Choice();
		choiceUserType.add("Cliente");
		choiceUserType.add("Operatore");
		choiceUserType.add("Administrator");
		GridBagConstraints gbc_choiceUserType = new GridBagConstraints();
		gbc_choiceUserType.anchor = GridBagConstraints.WEST;
		gbc_choiceUserType.insets = new Insets(0, 0, 5, 0);
		gbc_choiceUserType.gridx = 1;
		gbc_choiceUserType.gridy = 4;
		contentPane.add(choiceUserType, gbc_choiceUserType);
		GridBagConstraints gbc_btnAggiungi = new GridBagConstraints();
		gbc_btnAggiungi.insets = new Insets(0, 0, 0, 5);
		gbc_btnAggiungi.gridx = 0;
		gbc_btnAggiungi.gridy = 5;

		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(this);
		contentPane.add(btnAggiungi, gbc_btnAggiungi);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// aggiungi dati alla lista utenti
		if (e.getSource() == btnAggiungi) {
			Utente utente = new Utente(textFieldNome.getText().trim(),
					textFieldCognome.getText().trim(), textFieldUsername
							.getText().trim(), new String(
							passwordField.getPassword()).trim());
			switch (choiceUserType.getSelectedItem()) {
			case "Cliente": {
				utente.setUserType(Utente.CLIENTE);
				break;
			}
			case "Operatore": {
				utente.setUserType(Utente.OPERATORE);
				break;
			}
			case "Administrator": {
				utente.setUserType(Utente.ADMIN);
				break;

			}
			}

			model.addItem(utente);

			JOptionPane.showMessageDialog(null, new JLabel("Utente inserito"));
			setVisible(false);

		}
	}
}
