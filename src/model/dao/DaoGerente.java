package model.dao;

import java.util.List;

import model.entities.Gerente;

public interface DaoGerente {

	void insert(Gerente obj);
	void deleteById(Integer id);
	Gerente findById_partida(int id_partida);
	List<Gerente> findById_usuario(int id_usuario);
	List<Gerente> findAll();
}
