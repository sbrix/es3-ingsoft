package it.unipr.sbrix.esercizio2.VisteAzioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Volo;
import it.unipr.sbrix.esercizio2.Modelli.ModelVoli;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameAggiungiVolo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 435368945505380096L;
	private JPanel contentPane;
	private JTextField textFieldPartenza;
	private JTextField textFieldDestinazione;
	@SuppressWarnings("unused")
	private Agenzia ag = null;
	@SuppressWarnings("unused")
	private JPanel panel = null;
	private ModelVoli model = null;

	/**
	 * Create the frame.
	 */
	public FrameAggiungiVolo(final Agenzia ag_, final JPanel panel_,
			final ModelVoli model_) {

		ag = ag_;
		panel = panel_;
		model = model_;

		setResizable(false);
		setTitle("Aggiungi volo");
		setBounds(100, 100, 450, 128);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblPartenza = new JLabel("Partenza:");
		GridBagConstraints gbc_lblPartenza = new GridBagConstraints();
		gbc_lblPartenza.anchor = GridBagConstraints.EAST;
		gbc_lblPartenza.insets = new Insets(0, 0, 5, 5);
		gbc_lblPartenza.gridx = 0;
		gbc_lblPartenza.gridy = 0;
		contentPane.add(lblPartenza, gbc_lblPartenza);

		textFieldPartenza = new JTextField();
		GridBagConstraints gbc_textFieldPartenza = new GridBagConstraints();
		gbc_textFieldPartenza.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPartenza.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPartenza.gridx = 1;
		gbc_textFieldPartenza.gridy = 0;
		contentPane.add(textFieldPartenza, gbc_textFieldPartenza);
		textFieldPartenza.setColumns(10);

		JLabel lblDestinazione = new JLabel("Destinazione:");
		GridBagConstraints gbc_lblDestinazione = new GridBagConstraints();
		gbc_lblDestinazione.anchor = GridBagConstraints.EAST;
		gbc_lblDestinazione.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestinazione.gridx = 0;
		gbc_lblDestinazione.gridy = 1;
		contentPane.add(lblDestinazione, gbc_lblDestinazione);

		textFieldDestinazione = new JTextField();
		GridBagConstraints gbc_textFieldDestinazione = new GridBagConstraints();
		gbc_textFieldDestinazione.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDestinazione.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDestinazione.gridx = 1;
		gbc_textFieldDestinazione.gridy = 1;
		contentPane.add(textFieldDestinazione, gbc_textFieldDestinazione);
		textFieldDestinazione.setColumns(10);

		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Volo volo = new Volo();
				volo.partenza = textFieldPartenza.getText();
				volo.destinazione = textFieldDestinazione.getText();
				model.addItem(volo);
				setVisible(false);

			}
		});
		GridBagConstraints gbc_btnAggiungi = new GridBagConstraints();
		gbc_btnAggiungi.insets = new Insets(0, 0, 0, 5);
		gbc_btnAggiungi.gridx = 0;
		gbc_btnAggiungi.gridy = 2;
		contentPane.add(btnAggiungi, gbc_btnAggiungi);
	}

}
