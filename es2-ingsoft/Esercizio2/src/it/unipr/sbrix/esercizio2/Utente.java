package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Utente implements Serializable {

	public static final int ADMIN = 0;
	public static final int OPERATORE = 1;
	public static final int CLIENTE = 2;
	private static final long serialVersionUID = 3705447070498719951L;
	private int id;
	public String nome = null;
	public String cognome = null;
	public String userName = null;
	public String password = null;
	private int userType;

	@Override
	public String toString() {
		return "ID=" + id + ", Nome=" + nome + ", Cognome=" + cognome
				+ ", Username=" + userName + ", Tipo=" + userTypeToString();
	}

	Utente() {
	};

	public Utente(String nm, String cg, String un, String pw, Agenzia ag) {

		nome = nm;
		cognome = cg;
		userName = un;
		password = ag.passwordEncryptor.encryptPassword(pw);
		userType = Utente.CLIENTE;
		id = ag.idGlobaleUtenti++;
		ag.saveToFile(ag.fileIdUtenti, ag.idGlobaleUtenti);

	}

	public int getId() {
		return id;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String userTypeToString() {
		switch (this.userType) {
		case CLIENTE: {
			return new String("Cliente");
		}
		case OPERATORE: {
			return new String("Operatore");
		}
		case ADMIN: {
			return new String("Admin");
		}
		default:
			return null;
		}
	}
}
