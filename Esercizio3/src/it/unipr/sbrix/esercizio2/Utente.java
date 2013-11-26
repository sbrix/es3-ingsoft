package it.unipr.sbrix.esercizio2;

import java.io.Serializable;

public class Utente implements Serializable, Comparable<Utente> {

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

	public Utente() {
		super();
	}

	public Utente(String nm, String cg, String un, String pw) {

		nome = nm;
		cognome = cg;
		userName = un;
		password = Agenzia.passwordEncryptor.encryptPassword(pw);
		userType = Utente.CLIENTE;

	};

	public int getId() {
		return id;
	}

	public int getUserType() {
		return userType;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "ID=" + id + ", Nome=" + nome + ", Cognome=" + cognome
				+ ", Username=" + userName + ", Tipo=" + userTypeToString();
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

	@Override
	public int compareTo(Utente o) {
		// TODO Auto-generated method stub
		if (this.getId() > o.getId())
			return 1;
		if (this.getId() < o.getId())
			return -1;
		if (this.getId() == o.getId())
			return 0;
		return 0;
	}
}
