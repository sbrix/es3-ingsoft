package it.unipr.sbrix.esercizio2.VisteUtenti;

import it.unipr.sbrix.esercizio2.Agenzia;
import it.unipr.sbrix.esercizio2.Utente;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginWindow {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
					LoginWindow window = new LoginWindow();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JFrame frmLogin;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;

	private static Agenzia agenzia = null;

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			agenzia = new Agenzia();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 120);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		frmLogin.getContentPane().setLayout(gridBagLayout);

		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.fill = GridBagConstraints.VERTICAL;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 0;
		frmLogin.getContentPane().add(lblUsername, gbc_lblUsername);

		textFieldUsername = new JTextField();
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsername.gridx = 1;
		gbc_textFieldUsername.gridy = 0;
		frmLogin.getContentPane().add(textFieldUsername, gbc_textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.fill = GridBagConstraints.VERTICAL;
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		frmLogin.getContentPane().add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initUser();
			}
		});
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		frmLogin.getContentPane().add(passwordField, gbc_passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initUser();

			}

		});
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.fill = GridBagConstraints.BOTH;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 2;
		frmLogin.getContentPane().add(btnLogin, gbc_btnLogin);
		frmLogin.getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(
						new Component[] { lblUsername, textFieldUsername,
								lblPassword, passwordField, btnLogin }));
	}

	private void initUser() {
		// controllo se utente e cliente o operatore
		// System.out.println("gestione login");
		String name = new String(textFieldUsername.getText());
		String pwd = new String(passwordField.getPassword());
		boolean userFound = false;
		int id = 0;

		id = agenzia.modelUtenti.checkUserLogin(name, pwd);
		System.out.println("check utente ->id:" + id);
		if (id != -1) {
			final Utente utente = (Utente) agenzia.modelUtenti.getItem(id);

			switch (utente.getUserType()) {
			case Utente.CLIENTE: {
				userFound = true;
				System.out.println("avvio come utente ->id:" + id);

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager
									.getSystemLookAndFeelClassName());
							VistaCliente frameCliente = new VistaCliente(utente
									.getUserType(), utente.getId(), agenzia);

							frameCliente.setVisible(true);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// frmLogin.setVisible(false);
				break;
			}
			case Utente.OPERATORE: {
				userFound = true;

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager
									.getSystemLookAndFeelClassName());
							VistaOperatore frameOp = new VistaOperatore(utente
									.getUserType(), utente.getId(), agenzia);

							frameOp.setVisible(true);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				// frmLogin.setVisible(false);
				break;

			}
			case Utente.ADMIN: {
				userFound = true;
				/*
				 * VistaAdmin frameAdmin = new VistaAdmin(utente.getUserType(),
				 * utente.getId(), agenzia);
				 * 
				 * frameAdmin.setVisible(true);
				 */
				// frmLogin.setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager
									.getSystemLookAndFeelClassName());
							VistaAdmin frameAdmin = new VistaAdmin(utente
									.getUserType(), utente.getId(), agenzia);

							frameAdmin.setVisible(true);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
			}

		}

		if (!userFound) {
			JOptionPane.showMessageDialog(null, new JLabel(
					"Password o nome utente non valido"));
			textFieldUsername.setText(null);
			passwordField.setText(null);
		}
	}

}
