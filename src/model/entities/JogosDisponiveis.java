package model.entities;

import java.io.Serializable;

public class JogosDisponiveis implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id_partida;
	private String nome_partida;
	private int qtd_ingressos;
	
	public JogosDisponiveis() {
	}

	public JogosDisponiveis(int id_partida, String nome_partida, int qtd_ingressos) {
		this.id_partida = id_partida;
		this.nome_partida = nome_partida;
		this.qtd_ingressos = qtd_ingressos;
	}

	public int getId_partida() {
		return id_partida;
	}

	public void setId_partida(int id_partida) {
		this.id_partida = id_partida;
	}

	public String getNome_partida() {
		return nome_partida;
	}

	public void setNome_partida(String nome_partida) {
		this.nome_partida = nome_partida;
	}

	public int getQtd_ingressos() {
		return qtd_ingressos;
	}

	public void setQtd_ingressos(int qtd_ingressos) {
		this.qtd_ingressos = qtd_ingressos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_partida;
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
		JogosDisponiveis other = (JogosDisponiveis) obj;
		if (id_partida != other.id_partida)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id: " + id_partida + " - Nome Partida: " + nome_partida + " - Ingressos disponiveis: "
				+ qtd_ingressos;
	}	
	
}
