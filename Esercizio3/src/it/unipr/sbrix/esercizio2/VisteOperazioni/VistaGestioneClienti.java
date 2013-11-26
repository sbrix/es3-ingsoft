package it.unipr.sbrix.esercizio2.VisteOperazioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Modelli.ModelUtenti;
import it.unipr.sbrix.esercizio2.VisteAzioni.FrameAggiungiCliente;

import javax.swing.JPanel;

import java.awt.Color;
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

public class VistaGestioneClienti extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8129663312121425389L;
	private ModelUtenti model;
	private JPanel panelList = new JPanel();
	private JPanel panelButtons = new JPanel();
	private JButton btnAggiungi = null;
	private JButton btnRimuovi = null;
	private Agenzia ag = null;
	private final JLabel label = new JLabel("Gestione Utenti");
	private JTable table =null;
	private final JScrollPane scrollPane = new JScrollPane();

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public VistaGestioneClienti(Agenzia agenzia) {

		ag = agenzia;
		model = ag.modelClienti;
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
		
		add(panelList,gbc_panelList);
		label.setAlignmentX(0.5f);
		
		panelList.add(label);
		
		panelList.add(scrollPane);
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnAggiungi) {
			// aggiunta di un utente alla lista utenti
			JFrame frameAggiungiUser = new FrameAggiungiCliente(this.ag,
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
