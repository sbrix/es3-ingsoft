package it.unipr.sbrix.esercizio2.VisteOperazioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.VisteAzioni.FrameAggiungiUtente;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.GridLayout;
import java.awt.Component;

public class VistaGestioneUtenti extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7391320298284444080L;
	@SuppressWarnings("rawtypes")
	private JList list = null;
	private JPanel panelList = new JPanel();
	private JPanel panelButtons = new JPanel();
	private JButton btnAggiungi = null;
	private JButton btnRimuovi = null;
	private Agenzia ag = null;
	private final JScrollPane scrollPane = new JScrollPane();

	// private JScrollPane scrollPane = null;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VistaGestioneUtenti(final Agenzia agenzia) {
		ag = agenzia;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 780, 70, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_panelList = new GridBagConstraints();
		gbc_panelList.anchor = GridBagConstraints.NORTH;
		gbc_panelList.insets = new Insets(0, 0, 0, 5);
		gbc_panelList.gridx = 0;
		gbc_panelList.gridy = 0;
		add(panelList, gbc_panelList);
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));

		JLabel lblGestioneUtenti = new JLabel("Gestione Utenti");
		lblGestioneUtenti.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelList.add(lblGestioneUtenti);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panelList.add(scrollPane);

		list = new JList(ag.listaUtenti.toArray());

		scrollPane.setViewportView(list);
		scrollPane.setMinimumSize(new Dimension(780, 500));
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {

				panelList.invalidate();
				panelList.validate();
				panelList.repaint();

			}
		});
		list.setVisible(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.anchor = GridBagConstraints.NORTH;
		gbc_panelButtons.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelButtons.gridx = 1;
		gbc_panelButtons.gridy = 0;
		add(panelButtons, gbc_panelButtons);

		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(this);
		panelButtons.setLayout(new GridLayout(10, 1, 0, 0));
		panelButtons.add(btnAggiungi);

		btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(this);
		panelButtons.add(btnRimuovi);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnAggiungi) {
			// aggiunta di un utente alla lista utenti
			JFrame frameAggiungiUser = new FrameAggiungiUtente(this.ag,
					panelList, list);

			frameAggiungiUser.setVisible(true);
		}
		if (e.getSource() == btnRimuovi) {
			// rimuovi elementi selezionati
			int tmp = list.getSelectedIndex();
			if (tmp != -1) {
				System.out.println(tmp);
				ag.listaUtenti.remove(tmp);
				ag.saveToFile(ag.fileUtenti, ag.listaUtenti);
				list.setListData(ag.listaUtenti.toArray());
				panelList.revalidate();
				panelList.repaint();

			}

		}

	}

}
