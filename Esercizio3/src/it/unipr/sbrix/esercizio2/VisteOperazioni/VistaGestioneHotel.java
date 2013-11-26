package it.unipr.sbrix.esercizio2.VisteOperazioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Modelli.ModelHotel;
import it.unipr.sbrix.esercizio2.VisteAzioni.FrameAggiungiHotel;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaGestioneHotel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1847180396931611375L;
	/**
	 * Create the panel.
	 */

	private JPanel panelLista = new JPanel();
	private JButton btnAggiungi = new JButton("Aggiungi");
	private JPanel panelButtons = new JPanel();

	private JButton btnRimuovi = new JButton("Rimuovi");
	private final JLabel lblGestioneHotel = new JLabel("Gestione Hotel");
	private ModelHotel model = null;
	private JTable table = null;
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public VistaGestioneHotel(int uType, int id, final Agenzia ag) {
		model = ag.modelHotel;
		table = new JTable(model) {
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);

				// Alternate row color

				if (!isRowSelected(row))
					c.setBackground(row % 2 == 0 ? getBackground()
							: Color.LIGHT_GRAY);

				return c;
			}
		};
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 780, 70, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);
		GridBagConstraints gbc_panelLista = new GridBagConstraints();
		gbc_panelLista.fill = GridBagConstraints.BOTH;
		gbc_panelLista.insets = new Insets(0, 0, 0, 5);
		gbc_panelLista.gridx = 0;
		gbc_panelLista.gridy = 0;
		add(panelLista, gbc_panelLista);
		panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
		lblGestioneHotel.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelLista.add(lblGestioneHotel);

		panelLista.add(scrollPane);
		scrollPane.setViewportView(table);

		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.anchor = GridBagConstraints.NORTH;
		gbc_panelButtons.gridx = 1;
		gbc_panelButtons.gridy = 0;
		add(panelButtons, gbc_panelButtons);

		panelButtons.setLayout(new GridLayout(10, 1, 0, 0));
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameAggiungiHotel frame = new FrameAggiungiHotel(ag,
						panelLista, model);
				frame.setVisible(true);
			}
		});

		panelButtons.add(btnAggiungi);
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					model.removeItem(
							(int) model.getValueAt(table.getSelectedRow(), 0),
							table.getSelectedRow());

				}
			}
		});

		panelButtons.add(btnRimuovi);

		this.revalidate();
		this.repaint();
		this.setVisible(true);

	}

}
