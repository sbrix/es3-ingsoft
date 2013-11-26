package it.unipr.sbrix.esercizio2.VisteAzioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Volo;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JTable;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class FrameSelezioneVolo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1010086268734650308L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnSeleziona = null;
	private Agenzia ag = null;;

	/**
	 * Create the frame.
	 */
	public FrameSelezioneVolo(final Agenzia ag_, final Volo[] volo_,
			final JLabel label_) {
		setResizable(false);
		ag = ag_;
		setTitle("Seleziona volo");
		setBounds(100, 100, 514, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable(ag.modelVoli);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		btnSeleziona = new JButton("Seleziona");
		btnSeleziona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() != -1) {
					volo_[0] = (Volo) ag.modelVoli.getItem((int) ag.modelVoli
							.getValueAt(table.getSelectedRow(), 0));
					System.out.println(volo_[0].toString());
					// volo_.partenza = tmp.partenza;
					// volo_.destinazione = tmp.destinazione;
					// volo_.setId(tmp.getId());
					label_.setText(volo_[0].toString());
					setVisible(false);
				}
			}
		});

		GridBagConstraints gbc_btnSeleziona = new GridBagConstraints();
		gbc_btnSeleziona.anchor = GridBagConstraints.NORTH;
		gbc_btnSeleziona.gridx = 1;
		gbc_btnSeleziona.gridy = 0;
		contentPane.add(btnSeleziona, gbc_btnSeleziona);
	}

}
