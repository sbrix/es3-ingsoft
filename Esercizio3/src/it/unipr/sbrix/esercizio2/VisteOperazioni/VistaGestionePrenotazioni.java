package it.unipr.sbrix.esercizio2.VisteOperazioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Prenotazione;
import it.unipr.sbrix.esercizio2.Utente;
import it.unipr.sbrix.esercizio2.Vendita;
import it.unipr.sbrix.esercizio2.Modelli.ModelPrenotazioni;
import it.unipr.sbrix.esercizio2.Modelli.FilterPrenotazioniByUserId;
import it.unipr.sbrix.esercizio2.VisteAzioni.FrameAggiungiPrenotazione;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import org.joda.time.DateTime;

public class VistaGestionePrenotazioni extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5247151955150439392L;
	private JPanel panelLista = new JPanel();
	private JButton btnAggiungi = new JButton("Aggiungi");
	private JPanel panelButtons = new JPanel();
	private JButton btnCompra = new JButton("Acquista");
	private JButton btnRimuovi = new JButton("Rimuovi");
	private final JLabel lblGestionePrenotazioni = new JLabel(
			"Gestione prenotazioni");

	private JTable table = null;
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public VistaGestionePrenotazioni(int uType, final int id, final Agenzia ag) {
		table = new JTable(ag.modelPrenotazioni) {

			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);

				// Alternate row color

				if (!isRowSelected(row)) {

					Prenotazione tmp = (Prenotazione) ag.modelPrenotazioni
							.getItem((int) ag.modelPrenotazioni.getValueAt(row,
									0));
					DateTime dataScadenza = new DateTime(tmp.scadenza);
					// System.out.println("scadenza:"+dataScadenza.getMillis());
					DateTime dataDiOggi = new DateTime();
					// System.out.println("oggi:"+dataDiOggi.getMillis());
					if ((dataScadenza.getMillis() - dataDiOggi.getMillis()) <= ModelPrenotazioni.TRE_GIORNI_IN_MILLIS)
						c.setBackground(Color.ORANGE);
					else
						c.setBackground(row % 2 == 0 ? getBackground()
								: Color.LIGHT_GRAY);
				}

				return c;
			}
		};
		if (uType == Utente.CLIENTE) {
			// filtrare per id cliente
			System.out.println("avviato come utente ->id:" + id);

			TableRowSorter<ModelPrenotazioni> sorter = new TableRowSorter<ModelPrenotazioni>();
			sorter.setModel(ag.modelPrenotazioni);
			FilterPrenotazioniByUserId IDfilter = new FilterPrenotazioniByUserId();
			IDfilter.setUserId(id);
			sorter.setRowFilter(IDfilter);// <-------
			table.setRowSorter(sorter);

		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 780, 70, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);
		GridBagConstraints gbc_panelLista = new GridBagConstraints();
		gbc_panelLista.fill = GridBagConstraints.BOTH;
		gbc_panelLista.insets = new Insets(0, 0, 5, 5);
		gbc_panelLista.gridx = 0;
		gbc_panelLista.gridy = 0;
		add(panelLista, gbc_panelLista);
		panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
		lblGestionePrenotazioni.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelLista.add(lblGestionePrenotazioni);

		panelLista.add(scrollPane);
		scrollPane.setViewportView(table);

		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.insets = new Insets(0, 0, 5, 0);
		gbc_panelButtons.anchor = GridBagConstraints.NORTH;
		gbc_panelButtons.gridx = 1;
		gbc_panelButtons.gridy = 0;
		add(panelButtons, gbc_panelButtons);
		btnCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// trasforma prenotazione in vendita
				if (table.getSelectedRow() != -1) {
					Vendita vendita = new Vendita();
					Prenotazione prenotazione = (Prenotazione) ag.modelPrenotazioni
							.getItem((int) ag.modelPrenotazioni.getValueAt(
									table.getSelectedRow(), 0));
					vendita.andata = prenotazione.andata;
					vendita.cliente = prenotazione.cliente;
					vendita.durataPernottamento = prenotazione.durataPernottamento;
					vendita.hotel = prenotazione.hotel;
					vendita.idOperatore = prenotazione.idOperatore;
					vendita.ritorno = prenotazione.ritorno;
					ag.modelVendite.addItem(vendita);
					ag.modelPrenotazioni.removeItem(
							(int) ag.modelPrenotazioni.getValueAt(
									table.getSelectedRow(), 0),
							table.getSelectedRow());
				}
			}
		});
		panelButtons.setLayout(new GridLayout(10, 1, 0, 0));

		panelButtons.add(btnCompra);
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// rimuovi prenotazione
				if (table.getSelectedRow() != -1) {
					ag.modelPrenotazioni.removeItem(
							(int) ag.modelPrenotazioni.getValueAt(
									table.getSelectedRow(), 0),
							table.getSelectedRow());
				}
			}
		});

		panelButtons.add(btnRimuovi);

		panelButtons.add(btnAggiungi);

		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// aggiungi prenotazione
				FrameAggiungiPrenotazione frame = new FrameAggiungiPrenotazione(
						ag, id);
				frame.setVisible(true);
			}
		});
		if (uType == Utente.CLIENTE) {
			System.out.println("debug rimozione pulsante aggiungi");
			btnAggiungi.setVisible(false);

		}
		this.invalidate();
		this.validate();
		this.repaint();
		this.setVisible(true);

	}

}
