package model.entities;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id_usuario;
	private String cpf;
	private String senha;
	private String nome;
	private int idade;
	
	public Usuario(String cpf, String senha, String nome, int idade) {
		this.cpf = cpf;
		this.senha = senha;
		this.nome = nome;
		this.idade = idade;
	}

	public Usuario() {
	}
	
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Usuario other = (Usuario) obj;
		if (id_usuario != other.id_usuario)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id: " + id_usuario + " - CPF: " + cpf + " - Senha: " + senha + " - Nome=" + nome + " - Idade=" + idade;
	}
	
	
	
}
