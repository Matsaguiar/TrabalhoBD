package model.dao;

import java.util.List;

import model.entities.TipoEsporte;

public interface DaoTipoEsporte {

	void insert(TipoEsporte obj);
	void deleteByNome_esporte(String nome_esporte);
	List<TipoEsporte> findAll();
}
