package model.dao;

import java.util.List;

import model.entities.Estadio;

public interface DaoEstadio {

	void insert(Estadio obj);
	void update(Estadio obj);
	void deleteByNome(String nome);
	Estadio findByNome(String nome);
	Estadio findbtEstado(String estado);
	List<Estadio> findAll();
}