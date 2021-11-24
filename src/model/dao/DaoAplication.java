package model.dao;

import dbProperties.DB;
import model.dao.impl.CartaoDaoJDBC;
import model.dao.impl.EstadioDaoJDBC;
import model.dao.impl.GerenteDaoJDBC;
import model.dao.impl.IngressoDaoJDBC;
import model.dao.impl.JogosDisponiveisDaoJDBC;
import model.dao.impl.PartidaDaoJDBC;
import model.dao.impl.TelefoneDaoJDBC;
import model.dao.impl.TipoEsporteDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;

public class DaoAplication {

	public static DaoCartao createDaoCartao() {
		return new CartaoDaoJDBC(DB.getConnection());
	}
	
	public static DaoEstadio createDaoEstadio() {
		return new EstadioDaoJDBC(DB.getConnection());
	}
	
	public static DaoGerente createDaoGerente() {
		return new GerenteDaoJDBC(DB.getConnection());
	}
	
	public static DaoIngresso createDaoIngresso() {
		return new IngressoDaoJDBC(DB.getConnection());
	}
	
	public static DaoPartida createDaoPartida() {
		return new PartidaDaoJDBC(DB.getConnection());
	}
	
	public static DaoTelefone createDaoTelefone() {
		return new TelefoneDaoJDBC(DB.getConnection());
	}
	
	public static DaoTipoEsporte createDaoTipoEsporte() {
		return new TipoEsporteDaoJDBC(DB.getConnection());
	}
	
	public static DaoUsuario createDaoUsuario() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}

	public static DaoJogosDisponiveis createDaoJogosDisponiveis() {
		return new JogosDisponiveisDaoJDBC(DB.getConnection());
	}
}
