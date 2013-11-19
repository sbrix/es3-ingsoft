package it.unipr.sbrix.esercizio2.VisteOperazioni;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Utente;
import it.unipr.sbrix.esercizio2.Modelli.ModelUtenti;
import it.unipr.sbrix.esercizio2.VisteAzioni.FrameAggiungiClienteTable;
import it.unipr.sbrix.esercizio2.VisteAzioni.FrameAggiungiUtente;
import it.unipr.sbrix.esercizio2.VisteAzioni.FrameAggiungiUtenteTable;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;

public class VistaGestioneClientiTable extends JPanel implements ActionListener{
	private JTable table;
	private ModelUtenti modelUtenti;
	private JPanel panelList = new JPanel();
	private JPanel panelButtons=new JPanel();
	private JButton btnAggiungi = null;
	private JButton btnRimuovi = null;
	private final JScrollPane scrollPane = new JScrollPane();
	private Agenzia ag=null;

	/**
	 * Create the panel.
	 */
	public VistaGestioneClientiTable(Agenzia agenzia) {
		
		ag=agenzia;
		modelUtenti = new ModelUtenti(agenzia,ModelUtenti.INIT_CLIENTE);
		/*for(Utente i:ag.listaUtenti){
			modelUtenti.addRow(i);
		}*/
		
		//setLayout(new GridLayout(1, 2, 0, 0));
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

		

		
		
		add(panelList);
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.Y_AXIS));
		
		
		lblGestioneUtenti.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelList.add(lblGestioneUtenti);
		
		
		panelList.add(scrollPane);
		
		table = new JTable(modelUtenti);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane.setViewportView(table);
		
		scrollPane.setMinimumSize(new Dimension(780, 500));
	
		
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
		if (e.getSource()==btnAggiungi){
			// aggiunta di un utente alla lista utenti
						JFrame frameAggiungiUser = new FrameAggiungiClienteTable(this.ag,
								panelList,modelUtenti);

						frameAggiungiUser.setVisible(true);
		}
		if(e.getSource()==btnRimuovi){
			int index = 0;
			for (Utente i:ag.listaUtenti){
				
				if (i.getId() == (int) modelUtenti.getValueAt(table.getSelectedRow(), 0)){
					ag.listaUtenti.remove(index);
					ag.saveToFile(ag.fileUtenti, ag.listaUtenti);
					modelUtenti.removeRowRange(table.getSelectedRow(), table.getSelectedRow());
					break;
					
				}
				index++;
					
			}
			
		}
		
		
	}

}
