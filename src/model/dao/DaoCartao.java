package model.dao;

import java.util.List;

import model.entities.Cartao;

public interface DaoCartao {

	void insert(Cartao obj);
	void update (Cartao obj);
	void deleteById(Integer id);
	List<Cartao> findAll();
	List<Cartao> findByIdUsuario(int id_usuario);
}
