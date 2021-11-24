package model.dao;

import java.util.List;

import model.entities.JogosDisponiveis;

public interface DaoJogosDisponiveis {
	
	void insert(JogosDisponiveis obj);
	void update(JogosDisponiveis obj);
	void deleteById(Integer id);
	JogosDisponiveis findById(Integer id);
	List<JogosDisponiveis> findAll();

}