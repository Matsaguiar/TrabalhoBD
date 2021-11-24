package model.dao;

import model.entities.Ingresso;

public interface DaoIngresso {

	void insert(Ingresso obj);
	void deleteById(int id_partida);
	/*void update(Ingresso obj);
	void deleteById(Ingresso id);
	Ingresso findById(Integer id);
	Ingresso findByPartida(Integer idPartida);
	Ingresso findByEstadio(String nomeEstadio);
	List<Ingresso> findAll();*/
}
