package it.unipr.sbrix.esercizio2.VisteAzioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Hotel;
import it.unipr.sbrix.esercizio2.Modelli.ModelHotel;
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

public class FrameAggiungiHotel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 435368945505380096L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldVia;
	@SuppressWarnings("unused")
	private Agenzia ag = null;
	@SuppressWarnings("unused")
	private JPanel panel = null;
	private ModelHotel model = null;
	private JTextField textFieldCitta;
	private JTextField textFieldNazione;

	/**
	 * Create the frame.
	 */
	public FrameAggiungiHotel(final Agenzia ag_, final JPanel panel_,
			final ModelHotel model_) {

		ag = ag_;
		panel = panel_;
		model = model_;

		setResizable(false);
		setTitle("Aggiungi hotel");
		setBounds(100, 100, 519, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPane.add(lblNome, gbc_lblNome);

		textFieldNome = new JTextField();
		GridBagConstraints gbc_textFieldNome = new GridBagConstraints();
		gbc_textFieldNome.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNome.gridx = 1;
		gbc_textFieldNome.gridy = 0;
		contentPane.add(textFieldNome, gbc_textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblCitta = new JLabel("Citt\u00E0:");
		GridBagConstraints gbc_lblCitta = new GridBagConstraints();
		gbc_lblCitta.anchor = GridBagConstraints.EAST;
		gbc_lblCitta.insets = new Insets(0, 0, 5, 5);
		gbc_lblCitta.gridx = 0;
		gbc_lblCitta.gridy = 1;
		contentPane.add(lblCitta, gbc_lblCitta);

		textFieldCitta = new JTextField();
		GridBagConstraints gbc_textFieldCitta = new GridBagConstraints();
		gbc_textFieldCitta.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCitta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCitta.gridx = 1;
		gbc_textFieldCitta.gridy = 1;
		contentPane.add(textFieldCitta, gbc_textFieldCitta);
		textFieldCitta.setColumns(10);

		JLabel lblVia = new JLabel("Via:");
		GridBagConstraints gbc_lblVia = new GridBagConstraints();
		gbc_lblVia.anchor = GridBagConstraints.EAST;
		gbc_lblVia.insets = new Insets(0, 0, 5, 5);
		gbc_lblVia.gridx = 0;
		gbc_lblVia.gridy = 2;
		contentPane.add(lblVia, gbc_lblVia);

		textFieldVia = new JTextField();
		GridBagConstraints gbc_textFieldVia = new GridBagConstraints();
		gbc_textFieldVia.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldVia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVia.gridx = 1;
		gbc_textFieldVia.gridy = 2;
		contentPane.add(textFieldVia, gbc_textFieldVia);
		textFieldVia.setColumns(10);

		JLabel lblNazione = new JLabel("Nazione:");
		GridBagConstraints gbc_lblNazione = new GridBagConstraints();
		gbc_lblNazione.anchor = GridBagConstraints.EAST;
		gbc_lblNazione.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazione.gridx = 0;
		gbc_lblNazione.gridy = 3;
		contentPane.add(lblNazione, gbc_lblNazione);

		textFieldNazione = new JTextField();
		GridBagConstraints gbc_textFieldNazione = new GridBagConstraints();
		gbc_textFieldNazione.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNazione.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNazione.gridx = 1;
		gbc_textFieldNazione.gridy = 3;
		contentPane.add(textFieldNazione, gbc_textFieldNazione);
		textFieldNazione.setColumns(10);

		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Hotel hotel = new Hotel();
				hotel.citta = textFieldCitta.getText();
				hotel.nazione = textFieldNazione.getText();
				hotel.nome = textFieldNome.getText();
				hotel.via = textFieldVia.getText();

				model.addItem(hotel);
				setVisible(false);

			}
		});
		GridBagConstraints gbc_btnAggiungi = new GridBagConstraints();
		gbc_btnAggiungi.insets = new Insets(0, 0, 5, 5);
		gbc_btnAggiungi.gridx = 0;
		gbc_btnAggiungi.gridy = 4;
		contentPane.add(btnAggiungi, gbc_btnAggiungi);
	}

}
