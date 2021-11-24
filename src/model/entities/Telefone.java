package model.entities;

import java.io.Serializable;

public class Telefone implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id_usuario;
	private String fone;
	
	public Telefone(int id_usuario, String fone) {
		this.id_usuario = id_usuario;
		this.fone = fone;
	}
	
	public Telefone() {}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}
	
	
}
