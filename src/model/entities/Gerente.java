package model.entities;

import java.io.Serializable;

public class Gerente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id_usuario;
	private int id_partida;
	
	public Gerente(int id_usuario, int id_partida) {
		this.setId_usuario(id_usuario);
		this.id_partida = id_partida;
	}
	
	public Gerente() {}

	public int getId_partida() {
		return id_partida;
	}

	public void setId_partida(int id_partida) {
		this.id_partida = id_partida;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_partida;
		result = prime * result + id_usuario;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gerente other = (Gerente) obj;
		if (id_partida != other.id_partida)
			return false;
		if (id_usuario != other.id_usuario)
			return false;
		return true;
	}
	
	
}
