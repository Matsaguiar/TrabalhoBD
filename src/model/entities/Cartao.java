package model.entities;

import java.io.Serializable;

public class Cartao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id_cartao;
	private int id_usuario;
	private String numero;
	private String validade;
	private String cod_seguranca;
	
	
	public Cartao(int id_usuario, String numero, String validade, String cod_seguranca) {
		this.setId_usuario(id_usuario);
		this.numero = numero;
		this.validade = validade;
		this.cod_seguranca = cod_seguranca;
	}

	public Cartao() {
	}

	public int getId_cartao() {
		return id_cartao;
	}

	public void setId_cartao(int id_cartao) {
		this.id_cartao = id_cartao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getCod_segurança() {
		return cod_seguranca;
	}

	public void setCod_seguranca(String cod_segurança) {
		this.cod_seguranca = cod_segurança;
	}
	
	public int getId_usuario() {
		return id_usuario;
	}
	
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	

	/*@Override
	public int hashCode() {
		final int prime = 31;
		String result = 1;
		result = prime * result + cod_seguranca;
		result = prime * result + id_cartao;
		return result;
	}*/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		if (cod_seguranca != other.cod_seguranca)
			return false;
		if (id_cartao != other.id_cartao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id: " + id_cartao + " - Numero: " + numero + " - Validade: " + validade
				+ " - Codigo Seguranca: ***";
	}

	
	
}
