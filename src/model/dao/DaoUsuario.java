package model.dao;

import java.util.List;

import model.entities.Usuario;

public interface DaoUsuario {

	void insert(Usuario obj);
	void update(Usuario obj);
	void deleteBycpf(String cpf);
	Usuario findByCpf(String cpf);
	List<Usuario> findAll();
}
