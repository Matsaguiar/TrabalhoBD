package model.dao;

import model.entities.Telefone;

public interface DaoTelefone {

	void insert(Telefone obj);
	void update(String antigo, String novo);
	void deleteByFone(String fone);
	void findAll(Integer id_usuario);
}
