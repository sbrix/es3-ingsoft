package it.unipr.sbrix.esercizio2.VisteOperazioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Modelli.ModelUtenti;
import it.unipr.sbrix.esercizio2.VisteAzioni.FrameAggiungiUtente;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableCellRenderer;

public class VistaGestioneUtenti extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -670602422396241177L;
	private JTable table;
	private ModelUtenti model;
	private JPanel panelList = new JPanel();
	private JPanel panelButtons = new JPanel();
	private JButton btnAggiungi = null;
	private JButton btnRimuovi = null;
	private final JLabel lblGestioneUtenti = new JLabel("Gestione Utenti");
	private final JScrollPane scrollPane = new JScrollPane();
	private Agenzia ag = null;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public VistaGestioneUtenti(Agenzia agenzia) {

		
		ag = agenzia;

		model = ag.modelUtenti;
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

		GridBagConstraints gbc_panelList = new GridBagConstraints();
		gbc_panelList.fill = GridBagConstraints.BOTH;
		//gbc_panelList.anchor = GridBagConstraints.NORTH;
		gbc_panelList.insets = new Insets(0, 0, 0, 5);
		gbc_panelList.gridx = 0;
		gbc_panelList.gridy = 0;
		add(panelList, gbc_panelList);
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));

		// ------fin qui nessun problema nella visualizzazione

		lblGestioneUtenti.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelList.add(lblGestioneUtenti);
		scrollPane.setViewportView(table);
		scrollPane.setMinimumSize(new Dimension(780, 500));
		panelList.add(scrollPane);

		add(panelList,gbc_panelList);

		lblGestioneUtenti.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelList.add(lblGestioneUtenti);

		panelList.add(scrollPane);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// table.getColumnModel().getColumn(0).setPreferredWidth(50);
		// table.getColumnModel().getColumn(0).setResizable(false);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnAggiungi) {
			// aggiunta di un utente alla lista utenti
			JFrame frameAggiungiUser = new FrameAggiungiUtente(this.ag,
					panelList, model);

			frameAggiungiUser.setVisible(true);
		}
		if (e.getSource() == btnRimuovi) {
			if (table.getSelectedRow() != -1) {
				model.removeItem(
						(int) model.getValueAt(table.getSelectedRow(), 0),
						table.getSelectedRow());

			}

		}

	}

}
