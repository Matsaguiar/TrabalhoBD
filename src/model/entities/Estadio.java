package model.entities;

import java.io.Serializable;

public class Estadio implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id_estadio;
	private String nome_estadio;
	private int quant_ingresso;
	private String estado;
	private String cidade;
	
	public Estadio(String nome_estadio, int quant_ingresso, String estado, String cidade) {
		this.nome_estadio = nome_estadio;
		this.quant_ingresso = 1000;
		this.estado = estado;
		this.cidade = cidade;
	}

	public Estadio() {
	}

	
	public int getId_estadio() {
		return id_estadio;
	}

	public void setId_estadio(int id_estadio) {
		this.id_estadio = id_estadio;
	}

	public String getNome_estadio() {
		return nome_estadio;
	}

	public void setNome_estadio(String nome_estadio) {
		this.nome_estadio = nome_estadio;
	}

	public int getQuant_ingresso() {
		return quant_ingresso;
	}

	public void setQuant_ingresso(int quant_ingresso) {
		this.quant_ingresso = quant_ingresso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((nome_estadio == null) ? 0 : nome_estadio.hashCode());
		result = prime * result + quant_ingresso;
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
		Estadio other = (Estadio) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (nome_estadio == null) {
			if (other.nome_estadio != null)
				return false;
		} else if (!nome_estadio.equals(other.nome_estadio))
			return false;
		if (quant_ingresso != other.quant_ingresso)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nome do Estadio: " + nome_estadio + " - Capacidad: " + quant_ingresso + " - Estado: " + estado
				+ " - Cidade: " + cidade;
	}
	
	
	
}
