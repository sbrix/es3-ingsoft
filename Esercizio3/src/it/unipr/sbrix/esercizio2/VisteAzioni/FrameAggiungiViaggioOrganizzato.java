package it.unipr.sbrix.esercizio2.VisteAzioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Hotel;
import it.unipr.sbrix.esercizio2.ViaggioOrganizzato;
import it.unipr.sbrix.esercizio2.Volo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameAggiungiViaggioOrganizzato extends JFrame implements
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1328351513168186989L;
	private JPanel contentPane;
	private JTextField textFieldPernottamento;
	final private ViaggioOrganizzato viaggio = new ViaggioOrganizzato();
	private Agenzia ag = null;
	private JLabel lblAndatatostring = new JLabel("");
	private JButton btnSelezionaAndata = new JButton("Seleziona");
	private JLabel lblHoteltostring = new JLabel("");
	private JLabel lblRitornotostring = new JLabel("");
	private JButton btnSelezionaRitorno = new JButton("Seleziona");
	private JButton btnSelezionaHotel = new JButton("Seleziona");
	private Volo[] voloHackAndata = new Volo[1];
	private Volo[] voloHackRitorno = new Volo[1];
	private Hotel[] hotelHack = new Hotel[1];

	/**
	 * Create the frame.
	 */
	public FrameAggiungiViaggioOrganizzato(Agenzia ag_) {
		voloHackAndata[0] = new Volo();
		voloHackRitorno[0] = new Volo();
		hotelHack[0] = new Hotel();
		ag = ag_;
		setResizable(false);
		setTitle("Aggiungi viaggio organizzato");
		setBounds(100, 100, 611, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblVoloAndata = new JLabel("Volo andata:");
		GridBagConstraints gbc_lblVoloAndata = new GridBagConstraints();
		gbc_lblVoloAndata.anchor = GridBagConstraints.EAST;
		gbc_lblVoloAndata.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoloAndata.gridx = 0;
		gbc_lblVoloAndata.gridy = 0;
		contentPane.add(lblVoloAndata, gbc_lblVoloAndata);

		GridBagConstraints gbc_lblAndatatostring = new GridBagConstraints();
		gbc_lblAndatatostring.insets = new Insets(0, 0, 5, 5);
		gbc_lblAndatatostring.gridx = 1;
		gbc_lblAndatatostring.gridy = 0;
		contentPane.add(lblAndatatostring, gbc_lblAndatatostring);

		btnSelezionaAndata.addActionListener(this);
		GridBagConstraints gbc_btnSelezionaAndata = new GridBagConstraints();
		gbc_btnSelezionaAndata.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelezionaAndata.gridx = 2;
		gbc_btnSelezionaAndata.gridy = 0;
		contentPane.add(btnSelezionaAndata, gbc_btnSelezionaAndata);

		JLabel lblHotel = new JLabel("Hotel:");
		GridBagConstraints gbc_lblHotel = new GridBagConstraints();
		gbc_lblHotel.anchor = GridBagConstraints.EAST;
		gbc_lblHotel.insets = new Insets(0, 0, 5, 5);
		gbc_lblHotel.gridx = 0;
		gbc_lblHotel.gridy = 1;
		contentPane.add(lblHotel, gbc_lblHotel);

		GridBagConstraints gbc_lblHoteltostring = new GridBagConstraints();
		gbc_lblHoteltostring.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoteltostring.gridx = 1;
		gbc_lblHoteltostring.gridy = 1;
		contentPane.add(lblHoteltostring, gbc_lblHoteltostring);

		btnSelezionaHotel.addActionListener(this);
		GridBagConstraints gbc_btnSelezionaHotel = new GridBagConstraints();
		gbc_btnSelezionaHotel.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelezionaHotel.gridx = 2;
		gbc_btnSelezionaHotel.gridy = 1;
		contentPane.add(btnSelezionaHotel, gbc_btnSelezionaHotel);

		JLabel lblDurataPernottamento = new JLabel("Durata pernottamento:");
		GridBagConstraints gbc_lblDurataPernottamento = new GridBagConstraints();
		gbc_lblDurataPernottamento.anchor = GridBagConstraints.EAST;
		gbc_lblDurataPernottamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDurataPernottamento.gridx = 0;
		gbc_lblDurataPernottamento.gridy = 2;
		contentPane.add(lblDurataPernottamento, gbc_lblDurataPernottamento);

		textFieldPernottamento = new JTextField();
		GridBagConstraints gbc_textFieldPernottamento = new GridBagConstraints();
		gbc_textFieldPernottamento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPernottamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPernottamento.gridx = 1;
		gbc_textFieldPernottamento.gridy = 2;
		contentPane.add(textFieldPernottamento, gbc_textFieldPernottamento);
		textFieldPernottamento.setColumns(10);

		JLabel lblVoloRitorno = new JLabel("Volo ritorno:");
		GridBagConstraints gbc_lblVoloRitorno = new GridBagConstraints();
		gbc_lblVoloRitorno.anchor = GridBagConstraints.EAST;
		gbc_lblVoloRitorno.insets = new Insets(0, 0, 5, 5);
		gbc_lblVoloRitorno.gridx = 0;
		gbc_lblVoloRitorno.gridy = 3;
		contentPane.add(lblVoloRitorno, gbc_lblVoloRitorno);

		GridBagConstraints gbc_lblRitornotostring = new GridBagConstraints();
		gbc_lblRitornotostring.insets = new Insets(0, 0, 5, 5);
		gbc_lblRitornotostring.gridx = 1;
		gbc_lblRitornotostring.gridy = 3;
		contentPane.add(lblRitornotostring, gbc_lblRitornotostring);

		btnSelezionaRitorno.addActionListener(this);
		GridBagConstraints gbc_btnSelezionaRitorno = new GridBagConstraints();
		gbc_btnSelezionaRitorno.insets = new Insets(0, 0, 5, 0);
		gbc_btnSelezionaRitorno.gridx = 2;
		gbc_btnSelezionaRitorno.gridy = 3;
		contentPane.add(btnSelezionaRitorno, gbc_btnSelezionaRitorno);

		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				viaggio.andata = voloHackAndata[0];
				viaggio.ritorno = voloHackRitorno[0];
				viaggio.hotel = hotelHack[0];
				viaggio.durataPernottamento = Integer
						.valueOf(textFieldPernottamento.getText());
				ag.modelViaggi.addItem(viaggio);
				System.out.println(viaggio.toString());
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnAggiungi = new GridBagConstraints();
		gbc_btnAggiungi.insets = new Insets(0, 0, 0, 5);
		gbc_btnAggiungi.gridx = 0;
		gbc_btnAggiungi.gridy = 4;
		contentPane.add(btnAggiungi, gbc_btnAggiungi);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnSelezionaAndata) {
			FrameSelezioneVolo frame = new FrameSelezioneVolo(ag,
					voloHackAndata, lblAndatatostring);
			frame.setVisible(true);
		}
		if (e.getSource() == this.btnSelezionaRitorno) {
			FrameSelezioneVolo frame = new FrameSelezioneVolo(ag,
					voloHackRitorno, lblRitornotostring);
			frame.setVisible(true);
		}
		if (e.getSource() == btnSelezionaHotel) {
			FrameSelezioneHotel frame = new FrameSelezioneHotel(ag, hotelHack,
					lblHoteltostring);
			frame.setVisible(true);
		}

	}

}
