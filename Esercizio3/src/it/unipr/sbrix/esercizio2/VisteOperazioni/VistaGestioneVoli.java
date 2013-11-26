package it.unipr.sbrix.esercizio2.VisteOperazioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Modelli.ModelVoli;
import it.unipr.sbrix.esercizio2.VisteAzioni.FrameAggiungiVolo;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaGestioneVoli extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8926052849132831362L;
	private JPanel panelLista = new JPanel();
	private JButton btnAggiungi = new JButton("Aggiungi");
	private JPanel panelButtons = new JPanel();

	private JButton btnRimuovi = new JButton("Rimuovi");
	private final JLabel lblGestioneVoli = new JLabel("Gestione voli");
	private ModelVoli model = null;
	private JTable table = null;
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public VistaGestioneVoli(int uType, int id, final Agenzia ag) {
		model = ag.modelVoli;
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
		gbc_panelLista.insets = new Insets(0, 0, 5, 5);
		gbc_panelLista.gridx = 0;
		gbc_panelLista.gridy = 0;
		add(panelLista, gbc_panelLista);
		panelLista.setLayout(new BoxLayout(panelLista, BoxLayout.Y_AXIS));
		
		lblGestioneVoli.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelLista.add(lblGestioneVoli);

		panelLista.add(scrollPane);
		scrollPane.setViewportView(table);

		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.insets = new Insets(0, 0, 5, 0);
		gbc_panelButtons.anchor = GridBagConstraints.NORTH;
		gbc_panelButtons.gridx = 1;
		gbc_panelButtons.gridy = 0;
		add(panelButtons, gbc_panelButtons);

		panelButtons.setLayout(new GridLayout(10, 1, 0, 0));
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frameAggiungiVolo = new FrameAggiungiVolo(ag,
						panelLista, model);
				frameAggiungiVolo.setVisible(true);
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
