package model.entities;

import java.io.Serializable;

public class Partida implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id_partida;
	private String nome_partida;
	private int id_esporte;
	private String data_partida;
	private String horario_partida;
	private int id_estadio;
	private String nome_estadio;
	private int valor_ingresso;
	
	public Partida(int id_partida, String nome_partida, int id_esporte, String data_partida, String horario_partida, int id_estadio, int valor_ingresso) {
		this.id_partida = id_partida;
		this.nome_partida = nome_partida;
		this.id_esporte = id_esporte;
		this.data_partida = data_partida;
		this.horario_partida= horario_partida;
		this.id_estadio = id_estadio;
		this.valor_ingresso = valor_ingresso;
	}
	
	public Partida() {}


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

	public int getId_esporte() {
		return id_esporte;
	}

	public void setId_esporte(int id_esporte) {
		this.id_esporte = id_esporte;
	}

	public String getData_partida() {
		return data_partida;
	}

	public void setData_partida(String data_partida) {
		this.data_partida = data_partida;
	}

	public int getId_estadio() {
		return id_estadio;
	}

	public void setId_estadio(int id_estadio) {
		this.id_estadio = id_estadio;
	}

	public String getHorario_partida() {
		return horario_partida;
	}

	public void setHora_partida(String horario_partida) {
		this.horario_partida = horario_partida;
	}

	public String getNome_estadio() {
		return nome_estadio;
	}
	
	public void setNome_estadio(String nome_estadio) {
		this.nome_estadio = nome_estadio;
	}
	
	public int getValor_ingresso() {
		return valor_ingresso;
	}
	
	public void setValor_ingresso(int valor_ingresso) {
		this.valor_ingresso = valor_ingresso;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_esporte;
		result = prime * result + id_partida;
		result = prime * result + ((nome_partida == null) ? 0 : nome_partida.hashCode());
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
		Partida other = (Partida) obj;
		if (id_esporte != other.id_esporte)
			return false;
		if (id_partida != other.id_partida)
			return false;
		if (nome_partida == null) {
			if (other.nome_partida != null)
				return false;
		} else if (!nome_partida.equals(other.nome_partida))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id: " + id_partida + " - Nome Partida: " + nome_partida + " - Data Partida: " + data_partida + 
			   " - Nome estadio: " + nome_estadio + " - Valor ingresso: " + valor_ingresso;
	}

	
	
	
}
