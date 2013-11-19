package it.unipr.sbrix.esercizio2.VisteOperazioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Utente;
import it.unipr.sbrix.esercizio2.VisteAzioni.FrameAggiungiCliente;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Component;

public class VistaGestioneClienti extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8718440576509134386L;
	@SuppressWarnings("rawtypes")
	private JList list = null;
	private JPanel panelList = new JPanel();
	private JPanel panelButtons = new JPanel();
	private JButton btnAggiungi = null;
	private JButton btnRimuovi = null;
	private Agenzia ag = null;
	private final JScrollPane scrollPane = new JScrollPane();
	private ArrayList<Utente> listaClienti = null;

	// private JScrollPane scrollPane = null;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VistaGestioneClienti(final Agenzia agenzia) {
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

		JLabel lblGestioneClienti = new JLabel("Gestione Clienti");
		lblGestioneClienti.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelList.add(lblGestioneClienti);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panelList.add(scrollPane);

		listaClienti = new ArrayList<Utente>(0);
		for (Utente i : ag.listaUtenti) {
			if (i.getUserType() == Utente.CLIENTE) {
				listaClienti.add(i);
			}
		}

		list = new JList(listaClienti.toArray());

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
			JFrame frameAggiungiCliente = new FrameAggiungiCliente(this.ag,
					panelList, list);

			frameAggiungiCliente.setVisible(true);
		}
		if (e.getSource() == btnRimuovi) {
			// rimuovi elementi selezionati
			int tmp = list.getSelectedIndex();
			String selection = new String(list.getSelectedValue().toString());
			if (tmp != -1) {

				// System.out.println(list.getSelectedValue());
				int index = 0;
				for (Utente i : ag.listaUtenti) {
					// System.out.println(i.toString());
					if (i.toString().equals(selection)) {

						ag.listaUtenti.remove(index);

						break;

					}
					index++;
				}

				ag.saveToFile(ag.fileUtenti, ag.listaUtenti);
				listaClienti = new ArrayList<Utente>(0);
				for (Utente i : ag.listaUtenti) {
					if (i.getUserType() == Utente.CLIENTE) {
						listaClienti.add(i);
					}
				}
				list.setListData(listaClienti.toArray());
				panelList.revalidate();
				panelList.repaint();

			}

		}

	}

}
