package model.dao;

import java.util.List;

import model.entities.Partida;

public interface DaoPartida {

	void insert(Partida obj);
	void update(Partida obj);
	void deleteById(Integer id);
	List<Partida> findByEstadio(String nome_estadio);
	List<Partida> findByEsporte(String nome_esporte);
	List<Partida> findByEstado(String estado);
	List<Partida> findAll(int id_usuario);
	Partida findById_partida(Integer id_partida);
	List<Partida> todasPartidas();
}
